package logicunit.for_api;

import java.util.List;


public class BlogPost {
	public String title;
	public String author;
	public BlogPostType type;
	public int likes;
	public List<BlogPost> blogPosts;
	
	enum BlogPostType{
		NEWS,
		REVIEWS,
		GUIDE
	}
	
	public  static BlogPostType getType(BlogPost blogPost){
		return blogPost.type;
	}

}
