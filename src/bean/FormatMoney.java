package bean;

import java.text.DecimalFormat;

public class FormatMoney {
	static DecimalFormat formatter = new DecimalFormat("###,###,###");
	
	public static String format(int i) {
		return formatter.format(i);
	}

	public static String formatVnd(int i) {
		return format(i)+"đ";
	}
}
