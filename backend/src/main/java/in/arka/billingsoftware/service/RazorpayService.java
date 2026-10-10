package in.arka.billingsoftware.service;

import com.razorpay.RazorpayException;
import in.arka.billingsoftware.io.RazorpayOrderResponse;

public interface RazorpayService {
   RazorpayOrderResponse createOrder(Double amount, String currency) throws RazorpayException;
}
