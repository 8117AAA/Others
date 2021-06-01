package _Dao;

import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

import _Bean.Blog;

public class TimeSort implements Comparator<Object> {
	public int compare(Object o1,Object o2) {
		Blog blog1 = (Blog)o1;
		Blog blog2 = (Blog)o2;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date d1,d2;
		try {
			System.out.println(blog1.getBlogtime());
			System.out.println(blog2.getBlogtime());
			d1 = format.parse(blog1.getBlogtime());
			d2 = format.parse(blog2.getBlogtime());
		}catch(Exception ex) {
			ex.printStackTrace();
			return 0;
		}
		if(d1.before(d2))
			return 1;
		else
			return -1;
	}
}
