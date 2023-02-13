package business.order;

import api.ApiException;
import business.BookstoreDbException;
import business.JdbcUtils;
import business.book.Book;
import business.book.BookDao;
import business.cart.ShoppingCart;
import business.cart.ShoppingCartItem;
import business.customer.Customer;
import business.customer.CustomerDao;
import business.customer.CustomerForm;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.DateTimeException;
import java.time.YearMonth;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class DefaultOrderService implements OrderService {

	private BookDao bookDao;
	private CustomerDao customerDao;
	private OrderDao orderDao;
	private  LineItemDao lineItemDao;

	public void setBookDao(BookDao bookDao) {
		this.bookDao = bookDao;
	}
	public void setCustomerDao(CustomerDao customerDao){this.customerDao = customerDao;}
	public void  setOrderDao(OrderDao orderDao){this.orderDao = orderDao;}
	public void setLineItemDao(LineItemDao lineItemDao){this.lineItemDao = lineItemDao;}

	@Override
	public OrderDetails getOrderDetails(long orderId) {
		Order order = orderDao.findByOrderId(orderId);
		Customer customer = customerDao.findByCustomerId(order.getCustomerId());
		List<LineItem> lineItems = lineItemDao.findByOrderId(orderId);
		List<Book> books = lineItems
				.stream()
				.map(lineItem -> bookDao.findByBookId(lineItem.getBookId()))
				.collect(Collectors.toList());
		return new OrderDetails(order, customer, lineItems, books);
	}

	private int generateConfirmationNumber(){
		Random random = new Random();
		return random.nextInt(999999999);
	}

	private long performPlaceOrderTransaction(
			String name, String address, String phone,
			String email, String ccNumber, Date date,
			ShoppingCart cart, Connection connection) {
		try {
			connection.setAutoCommit(false);
			long customerId = customerDao.create(connection,name,address,phone,email,ccNumber,date);
			long customerOrderId = orderDao.create(
					connection,
					cart.getComputedSubtotal() + cart.getSurcharge(),
					generateConfirmationNumber(), customerId);
			for (ShoppingCartItem item : cart.getItems()) {
				lineItemDao.create(connection, customerOrderId,
						item.getBookId(), item.getQuantity());
			}
			connection.commit();
			return customerOrderId;
		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				throw new BookstoreDbException("Failed to roll back transaction", e1);
			}
			return 0;
		}
	}

	@Override
	public long placeOrder(CustomerForm customerForm, ShoppingCart cart) {

		validateCustomer(customerForm);
		validateCart(cart);

		try (Connection connection = JdbcUtils.getConnection()) {
			Date date = getDate(
					customerForm.getCcExpiryMonth(),
					customerForm.getCcExpiryYear());
			return performPlaceOrderTransaction(
					customerForm.getName(),
					customerForm.getAddress(),
					customerForm.getPhone(),
					customerForm.getEmail(),
					customerForm.getCcNumber(),
					date, cart, connection);
		} catch (SQLException e) {
			throw new BookstoreDbException("Error during close connection for customer order", e);
		}

	}

	private Date getDate(String monthString, String yearString) {
		Calendar c = Calendar.getInstance();
		c.set(Integer.parseInt(yearString), Integer.parseInt(monthString)-1, 1);  //January 30th 2021
		Date date = c.getTime();
		return c.getTime();
	}


	private void validateCustomer(CustomerForm customerForm) {

		if (!nameIsValid(customerForm.getName())) {
			throw new ApiException.InvalidParameter("Invalid name field");
		}

		if(!phoneIsValid(customerForm.getPhone())) {
			throw new ApiException.InvalidParameter("Invalid phone field");
		}

		if(!addressIsValid(customerForm.getAddress())) {
			throw new ApiException.InvalidParameter("Invalid address field");
		}

		if(!emailIsValid(customerForm.getEmail())) {
			throw new ApiException.InvalidParameter("Invalid email field");
		}

		if(!ccNumberIsValid(customerForm.getCcNumber())) {
			throw new ApiException.InvalidParameter("Invalid ccnumber field");
		}
		// TODO: Validation checks for address, phone, email, ccNumber

		if (!expiryDateIsInvalid(customerForm.getCcExpiryMonth(), customerForm.getCcExpiryYear())) {
			throw new ApiException.InvalidParameter("Invalid expiry date");

		}
	}

	private boolean nameIsValid(String name) {
		if(name == null) return false;
		if(name.equals("")) return false;
		if(name.length() < 4) return false;
		if(name.length() > 45) return false;

		return true;
	}

	private boolean phoneIsValid(String phone) {
		if(phone == null) return false;
		if(phone.equals("")) return false;

		//(503) 207-8726
		// 503-207-8736
		// 503 207 8736
		// get rid of parens, spaces and dashes

		phone = phone.replaceAll(" ", "");
		phone = phone.replaceAll("-", "");
		phone = phone.replaceAll("\\(", "");
		phone = phone.replaceAll("\\)", "");

		// should have a number(no letters) with exactly 10 digits
		if(!phone.matches("[\\d]+")) return false;
		if(phone.length()!= 10) return false;

		return true;
	}
	private boolean emailIsValid(String email) {
//		if(email == null) return false;
//		if(email.equals("")) return false;
//		if(!email.contains("@")) return false;
//		boolean flag = false;
//		//try find next project
//		for(int i=0; i<email.length(); i++) {
//			char ch = email.charAt(i);
//			if(flag == false && ch == '@') {
//				flag = true;
//			}
//			else if(flag == true && ch == '@') {
//				return false;
//			}
//		}
//		if(email.contains(" ")) return false;
//		if(email.charAt(email.length()-1)!='.') return false;
//		return true;
		String lastChar = String.valueOf(email.charAt(email.length()-1));
		if( email.equals("")) return false;
		if( email.contains(" ")) return false;
		if( email == null) return false;
		if( email.indexOf("@") == -1 ) return false;
		if( lastChar == ".") return false;

		return true;
	}

	private boolean addressIsValid(String address) {
		if(address == null) return false;
		if(address.equals("")) return false;
		if(address.length() < 4) return false;
		if(address.length() > 45) return false;
		return true;
	}

	private boolean ccNumberIsValid(String number) {
		if(number == null) return false;
		if(number.equals("")) return false;
		number = number.replaceAll(" ", "");
		number = number.replaceAll("-", "");
		if(number.length() < 14) return false;
		if(number.length() > 16) return false;
		return true;
	}


	private boolean expiryDateIsInvalid(String ccExpiryMonth, String ccExpiryYear) {
		// check logic
		YearMonth y = YearMonth.now();
		if(Integer.parseInt(ccExpiryYear) > y.getYear()) return true;
		if(Integer.parseInt(ccExpiryYear) == y.getYear() && Integer.parseInt(ccExpiryMonth) >= y.getMonthValue()) return true;


		// TODO: return true when the provided month/year is before the current month/yeaR
		// HINT: Use Integer.parseInt and the YearMonth class
		return false;

	}

	private void validateCart(ShoppingCart cart) {

		if (cart.getItems().size() <= 0) {
			throw new ApiException.InvalidParameter("Cart is empty.");
		}

		cart.getItems().forEach(item-> {
			if (item.getQuantity() < 0 || item.getQuantity() > 99) {
				throw new ApiException.InvalidParameter("Invalid quantity");
			}
			Book databaseBook = bookDao.findByBookId(item.getBookId());
			// TODO: complete the required validations

			if(item.getBookForm().getPrice() != databaseBook.getPrice()) {
				throw new ApiException.InvalidParameter("Invalid price");
			}

			if(item.getBookForm().getCategoryId() != databaseBook.getCategoryId()) {
				throw new ApiException.InvalidParameter("Invalid category");
			}


		});
	}

}
