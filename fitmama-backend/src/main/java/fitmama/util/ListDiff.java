package fitmama.util;

import java.util.ArrayList;
import java.util.List;

// ListDiff
public class ListDiff<T> {

	private List<T> leftList;
	private List<T> rightList;

	public ListDiff(List<T> leftList, List<T> rightList) {
		this.leftList = new ArrayList<>(leftList);
		this.rightList = new ArrayList<>(rightList);
	}

	public List<T> leftOnly() {
		List<T> tmp = new ArrayList<>(leftList);
		tmp.removeAll(rightList);
		return tmp;
	}

	public List<T> rightOnly() {
		List<T> tmp = new ArrayList<>(rightList);
		tmp.removeAll(leftList);
		return tmp;
	}

	public List<T> inCommon() {
		List<T> tmp = new ArrayList<>(rightList);
		tmp.retainAll(leftList);
		return tmp;
	}
}
