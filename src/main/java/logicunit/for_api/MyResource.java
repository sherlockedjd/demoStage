package logicunit.for_api;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

//import javax.servlet.ServletContext;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.encryption.InvalidPasswordException;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.apache.pdfbox.pdmodel.interactive.form.PDField;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;
import com.itextpdf.html2pdf.HtmlConverter;

//import io.swagger.annotations.Api;
import logicunit.for_api.BlogPost.BlogPostType;

/**
 * Root resource (exposed at "myresource" path)
 */
//@Api(value = "pet")
@Path("myresource")
public class MyResource {

	/**
	 * Method handling HTTP GET requests. The returned object will be sent to the
	 * client as "text/plain" media type.
	 *
	 * @return String that will be returned as a text/plain response.
	 * @throws IOException
	 * @throws InvalidPasswordException
	 */

	@GET
	@Path("/pdf")
	@Produces(MediaType.TEXT_PLAIN)
	public String gotIt() throws InvalidPasswordException, IOException {
		PDDocument pDDocument = PDDocument
				.load(new File(System.getProperty("user.home") + "\\Downloads\\" + "test.pdf"));
		PDAcroForm pDAcroForm = pDDocument.getDocumentCatalog().getAcroForm();
		List<PDField> fields = pDAcroForm.getFields();
		for (PDField field : fields) {
			System.out.println(field.getFullyQualifiedName());
			System.out.println(field.getFieldType().toString());
		}
		PDField field = pDAcroForm.getField("Tx_1");
		field.setValue("This is a first field printed by Java");
		field = pDAcroForm.getField("Tx_2");
		field.setValue("This is a second field printed by Java");
		pDDocument.save(System.getProperty("user.home") + "\\Downloads\\" + "pdf-java-output.pdf");
		pDDocument.close();
		return "pdf generated sucessfully";
	}

	@GET
	@Path("/pdf/html")
	@Produces(MediaType.TEXT_PLAIN)
	public String gotItHtml() throws InvalidPasswordException, IOException {
		String HTML = "<h1>Hello</h1>" + "<p>This was created using iText</p>"
				+ "<a href='hmkcode.com'>hmkcode.com</a>";
		HtmlConverter.convertToPdf(HTML,
				new FileOutputStream(System.getProperty("user.home") + "\\Downloads\\" + "string-to-pdf.pdf"));
		return "pdf generated sucessfully";
	}

//	@GET
//	@Path("/html")
//	@Produces(MediaType.TEXT_HTML)
//	public String gotItHtmlTemplate(@Context ServletContext ctx) throws InvalidPasswordException, IOException {
//		 MustacheFactory mf = new DefaultMustacheFactory();
//		 //String path="C:\\users\\THE LAPTOP STORE\\Downloads\todo.mustache";
//		 Mustache m = mf.compile("todonested.mustache");
//		 Todo_Likes likes=new Todo_Likes();
//		 likes.setCount(3);
//		 List<Todo> todos=new ArrayList<Todo>();
//		 List<Todo_Tasks> todo_tasks=new ArrayList<Todo_Tasks>();
//		 Todo_Tasks todo_task=new Todo_Tasks();
//		 todo_task.setId(1);
//		 todo_task.setDescription("task #1 description for todo #1");
//		 todo_task.setStatus("completed");
//		 todo_task.setTodo_Id(1);
//		 todo_tasks.add(todo_task);
//		 todo_task=new Todo_Tasks();
//		 todo_task.setId(2);
//		 todo_task.setDescription("task #2 description for todo #1");
//		 todo_task.setStatus("completed");
//		 todo_task.setTodo_Id(1);
//		 todo_tasks.add(todo_task);
//		 todo_task=new Todo_Tasks();
//		 todo_task.setId(3);
//		 todo_task.setDescription("task #3 description for todo #1");
//		 todo_task.setStatus("completed");
//		 todo_task.setTodo_Id(1);
//		 todo_tasks.add(todo_task);
//		 Todo todo = new Todo("Todo 1", "Sample Description",true,likes);
//		 todo.setId(1);
//		 todo.setTasks(todo_tasks);
//		 todos.add(todo);
//		 likes=new Todo_Likes();
//		 likes.setCount(70);
//		 todo_tasks=new ArrayList<Todo_Tasks>();
//		 todo_task=new Todo_Tasks();
//		 todo_task.setId(1);
//		 todo_task.setDescription("task #1 description for todo #2");
//		 todo_task.setStatus("completed");
//		 todo_task.setTodo_Id(2);
//		 todo_tasks.add(todo_task);
//		 todo_task=new Todo_Tasks();
//		 todo_task.setId(2);
//		 todo_task.setDescription("task #2 description for todo #2");
//		 todo_task.setStatus("completed");
//		 todo_task.setTodo_Id(2);
//		 todo_tasks.add(todo_task);
//		 todo_task=new Todo_Tasks();
//		 todo_task.setId(3);
//		 todo_task.setDescription("task #3 description for todo #2");
//		 todo_task.setStatus("completed");
//		 todo_task.setTodo_Id(2);
//		 todo_tasks.add(todo_task);
//		 todo=new Todo("Todo 2", "Sample Description",false,likes);
//		 todo.setId(2);
//		 todo.setTasks(todo_tasks);
//		 todos.add(todo);
//		 likes=new Todo_Likes();
//		 likes.setCount(300);
//		 todo_tasks=new ArrayList<Todo_Tasks>();
//		 todo_task=new Todo_Tasks();
//		 todo_task.setId(1);
//		 todo_task.setDescription("task #1 description for todo #3");
//		 todo_task.setStatus("completed");
//		 todo_task.setTodo_Id(3);
//		 todo_tasks.add(todo_task);
//		 todo_task=new Todo_Tasks();
//		 todo_task.setId(2);
//		 todo_task.setDescription("task #2 description for todo #3");
//		 todo_task.setStatus("completed");
//		 todo_task.setTodo_Id(3);
//		 todo_tasks.add(todo_task);
//		 todo_task=new Todo_Tasks();
//		 todo_task.setId(3);
//		 todo_task.setDescription("task #3 description for todo #3");
//		 todo_task.setStatus("completed");
//		 todo_task.setTodo_Id(3);
//		 todo_tasks.add(todo_task);
//		 todo=new Todo("Todo 3", "Sample Description",true,likes);
//		 todo.setId(3);
//		 todo.setTasks(todo_tasks);
//		 todos.add(todo);
//		 
//		 Map<String, Object> context = new HashMap<>();
//		    context.put("todos", todos);
//		    context.put("title", "To Do App");
//		    context.put("text", "List of ToDos");
//		 
//		 StringWriter writer = new StringWriter();
//		 //StringWriter v=(StringWriter) m.execute(writer, todo);
//		 StringWriter v=(StringWriter) m.execute(writer, context);
//		 v.flush();
//		 String HTML = writer.toString();
//		 System.out.println("returning response");
//		 HtmlConverter.convertToPdf(HTML,
//					new FileOutputStream(System.getProperty("user.home") + "\\Downloads\\" + "string-to-pdf.pdf"));
//        return HTML;
//	}

//	@GET
//	@Path("/{id}")
//	@Produces(MediaType.TEXT_PLAIN)
	public String getIt(@PathParam("id") String id) {
		List<BlogPost> blogPosts = new ArrayList<BlogPost>();
		BlogPost blogPost = new BlogPost();
		blogPost.title = "covid-19 and distance learning";
		blogPost.likes = 35;
		blogPost.author = "Dr jerald Shastri";
		blogPost.type = BlogPostType.REVIEWS;
		blogPosts.add(blogPost);
		blogPost = new BlogPost();
		blogPost.title = "covid-19 and virtual class rooms";
		blogPost.likes = 35;
		blogPost.author = "Mr Q";
		blogPost.type = BlogPostType.REVIEWS;
		blogPosts.add(blogPost);
		blogPost = new BlogPost();
		blogPost.title = "covid-19's impact on air travelling industry";
		blogPost.likes = 35;
		blogPost.author = "kunwar shamshad";
		blogPost.type = BlogPostType.NEWS;
		blogPosts.add(blogPost);
		blogPost = new BlogPost();
		blogPost.title = "corona safety guidelines";
		blogPost.likes = 35;
		blogPost.author = "Mr Q";
		blogPost.type = BlogPostType.GUIDE;
		blogPosts.add(blogPost);
		List<BlogPost> groupedBy = new ArrayList<BlogPost>();
		Map<BlogPostType, List<BlogPost>> postsPerType = blogPosts.stream()
				.collect(Collectors.groupingBy(BlogPost::getType));
		groupedBy = postsPerType.get(BlogPostType.REVIEWS);
		System.out.println("total no of posts per type REVIEWS are " + groupedBy.size());
		groupedBy = postsPerType.get(BlogPostType.NEWS);
		System.out.println("total no of posts per type NEWS are " + groupedBy.size());
		groupedBy = postsPerType.get(BlogPostType.GUIDE);
		System.out.println("total no of posts per type GUIDE are " + groupedBy.size());
		String ddate = LocalDate.parse("2020-07-31").minusMonths(1).toString();
		System.out.println(ddate);
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate localDate = LocalDate.now();
		System.out.println("the day of month is" + localDate.getDayOfMonth());
//    	LocalDate dateInFuture=LocalDate.parse(dtf.format(localDate)).plusMonths(6);
//    	LocalDate dateInPast=LocalDate.parse(dtf.format(localDate)).minusMonths(6);
//    	System.out.println("date in future 6 months "+dateInFuture.toString());
//    	System.out.println("date in past 6 months "+dateInPast.toString());
//    	if(localDate.isAfter(dateInPast)&&localDate.isBefore(dateInFuture)) {
//    		
//    	}
		String date = LocalDate.parse(dtf.format(localDate)).plusDays(30).toString();
		String previousDate = LocalDate.parse(dtf.format(localDate)).minusDays(30).toString();
		return "Got it!" + id + "dated: " + date + " for period " + previousDate;
	}
	
	@POST
	@Path("/pdf/html")
	@Produces(MediaType.TEXT_PLAIN)
	public String gotItHtml2(BlogPost blogPost) throws InvalidPasswordException, IOException {
		String HTML = "<h1>Hello</h1>" + "<p>This was created using iText</p>"
				+ "<a href='hmkcode.com'>hmkcode.com</a>";
		HtmlConverter.convertToPdf(HTML,
				new FileOutputStream(System.getProperty("user.home") + "\\Downloads\\" + "string-to-pdf.pdf"));
		return "pdf generated sucessfully";
	}
}
