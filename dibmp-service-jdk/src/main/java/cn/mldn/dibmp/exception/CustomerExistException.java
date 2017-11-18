package cn.mldn.dibmp.exception;

@SuppressWarnings("serial")
public class CustomerExistException extends RuntimeException {
   public CustomerExistException(String msg) {
	   super(msg);
   }
}
