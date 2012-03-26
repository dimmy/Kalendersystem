package no.ntnu.fp.model.ref;

public class RefCounter<T> {
	private T obj;
	private int counter;
	
	public RefCounter(T object) {
		obj = object;
		counter = 1;
	}
	
	public void ref() {
		counter++;
	}
	
	/**
	 * Returns true if reference counter is 0 or less
	 * @return
	 */
	public boolean unref() {
		counter--;
		return counter < 1;
	}
	
	public T get() {
		return obj;
	}
	
	public void set(T object) {
		obj = object;
	}
}
