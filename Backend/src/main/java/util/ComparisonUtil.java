/**
 * 
 */
package util;

import java.util.List;

import data.model.Post;
import data.model.User;

/**
 * @author Calvin Cheng
 *
 */
public class ComparisonUtil {
	public static boolean postHasUserLike(Post p, User u) {
		List<User> ulist = p.getLikedUsers();
		for(int i = 0; i<ulist.size(); i++) {
			if(ulist.get(i).getEmail().equals(u.getEmail())) {
				return true;
			}
		}
		
		return false;
	}
	
	public static boolean userHasPostLike(Post p, User u) {
		List<Post> plist = u.getLikedPosts();
		for(int i = 0; i<plist.size(); i++) {
			if(plist.get(i).getPostId() ==  p.getPostId()) {
				return true;
			}
		}
		
		return false;
	}
	
	public static void removeUser(Post p, User u) {
		List<User> ulist = p.getLikedUsers();
		for(int i = 0; i<ulist.size(); i++) {
			if(ulist.get(i).getEmail().equals(u.getEmail())) {
				p.getLikedUsers().remove(i);
				i = -1;
			}
		}
	}
	public static void removePost(Post p, User u) {
		List<Post> plist = u.getLikedPosts();
		for(int i = 0; i<plist.size(); i++) {
			if(plist.get(i).getPostId() ==  p.getPostId()) {
				u.getLikedPosts().remove(i);
				i = -1;
			}
		}
		
	}

}
