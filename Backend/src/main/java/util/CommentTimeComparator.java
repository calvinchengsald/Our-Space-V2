package util;

import java.util.Comparator;

import data.model.Comment;

public class CommentTimeComparator implements Comparator<Comment> {

	@Override
	public int compare(Comment o1, Comment o2) {
		return o1.getCreated().getSeconds() - o2.getCreated().getSeconds();
	}

	
}
