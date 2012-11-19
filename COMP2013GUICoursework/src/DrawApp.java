import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.imageio.ImageIO;

import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Paint;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class DrawApp extends Application{
	
	private double width;
	private double height;
	private Paint color;
	private boolean end;
	private int step;
	private ArrayList<String> list;
	private Paint turtleColor;
	private double turtleSize;
	
  public static void main(String[] args){
    launch(args);
  }
  
  public double getWidth(){ return width; } 
  public double getHeight(){ return height; }

  
  @Override
  public void start(Stage stage){
	  //default width and height are 500 and 300 (+125 because it needs area for message box)
	  width = 500;
	  height = 300 + 125;
	  //default color is black
	  color = Color.BLACK;
	  //default turtle is black, and size is two
	  turtleColor = Color.BLACK;
	  turtleSize = 2;
	  end = false;
	  step = 0;
	  init(stage);
	  stage.show();
  }
  
  private void init(final Stage stage){
	  list = getLines(new InputStreamReader(System.in));
	  
	  final Group parent = new Group();
	  stage.setResizable(false);
	  stage.setTitle("Draw App");
	  stage.setScene(new Scene(parent,width,height));
	  
	  Rectangle messageBox = new Rectangle(5,height-125,width-10,120);
	  messageBox.setFill(Color.GRAY);
	  parent.getChildren().add(messageBox);
	  
	  final Group save = new Group();
	  final Button three = new Button();
	  three.setText("Save Image to File");
	  three.setOnAction(new EventHandler<ActionEvent>(){
		  @Override
		  public void handle(ActionEvent event) {
			  //save to file
			  try{
				  ImageIO.write(SwingFXUtils.fromFXImage(stage.getScene().snapshot(null), null),
				  "png",
				  new File("savedFile.png"));
			  }catch(Exception e){
				  Stage ems = new Stage(StageStyle.UTILITY);
				  Group g = new Group();
				  ems.setScene(new Scene(g,200,20));
				  ems.setTitle("Error");
				  Text em = new Text(15,15,"Error Writing File");
				  em.setFont(new Font(10));
				  g.getChildren().add(em);
				  ems.show();
			  }
		  }
	  });
	  save.getChildren().add(three);
	  save.setTranslateX(width-150);
	  save.setTranslateY(height-30);
	  
	  final Group btn1 = new Group();
	  final Button one = new Button();
	  one.setText("Execute the program non-stopping");
	  one.setOnAction(new EventHandler<ActionEvent>(){
		  @Override
		  public void handle(ActionEvent event) { 
			  Group parent = new Group();

			  Group display = readInput(Integer.MAX_VALUE);
			  parent.getChildren().add(display);
			  parent.getChildren().add(save);
			  save.setTranslateX(width-150);
			  save.setTranslateY(height-30);

			  stage.setResizable(false);
			  stage.setTitle("Draw App");
			  stage.setScene(new Scene(parent,width,height));
		  }
	  });
	  btn1.getChildren().add(one);
	  btn1.setTranslateX(15);
	  btn1.setTranslateY(height-120);
	  
	  final Group btn2 = new Group();
	  final Button two = new Button();
	  two.setText("Execute single line");
	  two.setOnAction(new EventHandler<ActionEvent>(){
		  @Override
		  public void handle(ActionEvent event) {
			  step++;
			  Group parent = new Group();

			  Group display = readInput(step);
			  parent.getChildren().add(display);
			  if(!end){
				  parent.getChildren().add(btn1);
				  parent.getChildren().add(btn2);
			  }else{
				  parent.getChildren().add(save);
			  }
			  save.setTranslateX(width-150);
			  save.setTranslateY(height-30);

			  stage.setResizable(false);
			  stage.setTitle("Draw App");
			  stage.setScene(new Scene(parent,width,height));
		  }
	  });
	  btn2.getChildren().add(two);
	  btn2.setTranslateX(15);
	  btn2.setTranslateY(height-80);
	  
	  parent.getChildren().add(btn1);
	  parent.getChildren().add(btn2);
	  
  }
  
  private ArrayList<String> getLines(InputStreamReader read){
	  ArrayList<String> als = new ArrayList<String>();
	  try{
		  BufferedReader rd = new BufferedReader(read);
		  String line = rd.readLine();
		  while(line!=null){
			  als.add(line);
			  line = rd.readLine();
		  }
	  }catch(Exception e){  
	  }
	  return als;
  }
  
  private Group readInput(int numStep){
	  Group gr = new Group();
	  Text message;
	  int numLines = 0;
	  try{
		  for(int i=0;i<list.size();i++){
			  numLines++;
			  parseLine(gr,list.get(i));
			  if(numLines==numStep&&!(numStep==list.size())){
				  Rectangle messageBox = new Rectangle(5,height-125,width-10,120);
				  messageBox.setFill(Color.GRAY);
				  gr.getChildren().add(messageBox);
				  return gr;
			  }
		  }
		  message = new Text(15,height-100,"Drawing Image Completed.");
		  message.setFill(Color.BLACK);
	  }catch(Exception e){
		  message = new Text(15,height-100,"Exception thrown while reading line "+numLines+".\n"+e.toString());
		  message.setFill(Color.RED);
	  }
	  end = true;
	  message.setFont(new Font(20));
	  message.setWrappingWidth(width-20);
	  Rectangle messageBox = new Rectangle(5,height-125,width-10,120);
	  messageBox.setFill(Color.GRAY);
	  gr.getChildren().add(messageBox);
	  gr.getChildren().add(message);
	  return gr;
  }
  
  private void parseLine(Group gr, String line) throws Exception{
	  String command = line.substring(0, 2);
	  StringTokenizer param = new StringTokenizer(line.substring(2));
	  if(command.equals("DL")){
		  drawLine(gr,toDouble(param),toDouble(param),toDouble(param),toDouble(param));
	  }else if(command.equals("DR")){
		  drawRect(gr,toDouble(param),toDouble(param),toDouble(param),toDouble(param));
	  }else if(command.equals("DO")){
		  drawOval(gr,toDouble(param),toDouble(param),toDouble(param),toDouble(param));
	  }else if(command.equals("FR")){
		  fillRect(gr,toDouble(param),toDouble(param),toDouble(param),toDouble(param));
	  }else if(command.equals("DA")){
		  drawArc(gr,toDouble(param),toDouble(param),toDouble(param),toDouble(param),toDouble(param),toDouble(param));
	  }else if(command.equals("DS")){
		  drawString(gr,param.nextToken(),toDouble(param),toDouble(param));
	  }else if(command.equals("FO")){
		  fillOval(gr,toDouble(param),toDouble(param),toDouble(param),toDouble(param));
	  }else if(command.equals("SC")){
		  setColor(param.nextToken());
	  }else if(command.equals("CD")){
		  changeDimension(toDouble(param),toDouble(param));
	  }else if(command.equals("DI")){
		  displayImage(gr,toDouble(param),toDouble(param),toDouble(param),toDouble(param),param.nextToken());
	  }else if(command.equals("CG")){
		  setColorGradient(toDouble(param),toDouble(param),toDouble(param),toDouble(param),Integer.parseInt(param.nextToken()),param.nextToken(),param.nextToken(),param.nextToken(),toDouble(param));
	  }else if(command.equals("DP")){
		  ArrayList<Double> lists = new ArrayList<Double>();
		  while(param.hasMoreTokens()){
			  lists.add(toDouble(param));
		  }
		  double[] points = new double[lists.size()];
		  for(int i=0;i<points.length;i++){
			  points[i] = lists.get(i);
		  }
		  drawPolygon(gr,points);
	  }else if(command.equals("FP")){
		  ArrayList<Double> lists = new ArrayList<Double>();
		  while(param.hasMoreTokens()){
			  lists.add(toDouble(param));
		  }
		  double[] points = new double[lists.size()];
		  for(int i=0;i<points.length;i++){
			  points[i] = lists.get(i);
		  }
		  fillPolygon(gr,points);
	  }else if(command.equals("TL")){
		  drawLine(gr,toDouble(param),toDouble(param),toDouble(param),toDouble(param),turtleColor,turtleSize);
	  }else if(command.equals("TC")){
		  setTurtleColor(param.nextToken());
	  }else if(command.equals("TS")){
		  setTurtleSize(toDouble(param));
	  }
  }
  
  private void setTurtleSize(double size) {
	turtleSize = size;	
}

private void drawPolygon(Group gr, double[] points) {
		Polygon pl = new Polygon(points);
		pl.setFill(Color.TRANSPARENT);
		pl.setStroke(color);
		pl.setStrokeWidth(2);
		gr.getChildren().add(pl);
	}
  
  private void fillPolygon(Group gr, double[] points) {
	Polygon pl = new Polygon(points);
	pl.setFill(color);
	gr.getChildren().add(pl);
}

private void setColorGradient(double x1, double y1, double x2, double y2, int proportional, String cycleMethod, String color1, String color2, double offset) {
	  boolean p = !(proportional==0);
	  CycleMethod cm = CycleMethod.valueOf(cycleMethod);
	  Stop[] stops = new Stop[] { new Stop(0, Color.valueOf(color1)), new Stop(offset, Color.valueOf(color2))};
	  LinearGradient lg = new LinearGradient(x1,y1,x2,y2,p,cm,stops);
	  color = lg;	
}

private void displayImage(Group gr, double x, double y, double width, double height, String fileName) {
	Image im = new Image(fileName);
	ImageView iv = new ImageView();
	iv.setImage(im);
	iv.setX(x);
	iv.setY(y);
	iv.setFitWidth(width);
	iv.setFitHeight(height);
	gr.getChildren().add(iv);
  }

private void changeDimension(double x, double y) throws Exception{
	  if(x<=0){
		  throw new TooSmallValueException("width",x);
	  }else if(y<=0){
		  throw new TooSmallValueException("height",y);
	  }
	  width = x;
	  height = y + 125;
  }
  
  private void setColor(String col) throws Exception{
	    color = Color.valueOf(col);
  }
  
  private void setTurtleColor(String col) throws Exception{
	    turtleColor = Color.valueOf(col);
}

private void fillOval(Group gr, double x, double y, double w, double h) {
	Ellipse el = new Ellipse(x,y,w,h);
	el.setFill(color);
	gr.getChildren().add(el);
}

private void drawString(Group gr, String str, double x, double y) {
	Text tx = new Text(x,y,str);
	tx.setFill(color);
	tx.setFont(new Font(15));
	gr.getChildren().add(tx);	
}

private void drawArc(Group gr, double centerX, double centerY, double radiusX, double radiusY, double startAngle, double length) {
	Arc ar = new Arc(centerX,centerY,radiusX,radiusY,startAngle,length);
	ar.setStroke(color);
	gr.getChildren().add(ar);	
}

private void fillRect(Group gr, double x, double y, double w, double h) {
	Rectangle rc = new Rectangle(x,y,w,h);
	rc.setFill(color);
	gr.getChildren().add(rc);
}

private void drawOval(Group gr, double x, double y, double w, double h) {
	Ellipse el = new Ellipse(x,y,w,h);
	el.setFill(Color.TRANSPARENT);
	el.setStroke(color);
	el.setStrokeWidth(2);
	gr.getChildren().add(el);
}

private void drawRect(Group gr, double x, double y, double w, double h) {
	Rectangle rc = new Rectangle(x,y,w,h);
	rc.setFill(Color.TRANSPARENT);
	rc.setStroke(color);
	rc.setStrokeWidth(2);
	gr.getChildren().add(rc);
}

private void drawLine(Group gr, double x1, double y1, double x2, double y2, Paint c, double size) {
	Line ln = new Line(x1,y1,x2,y2);
	ln.setStroke(c);
	ln.setStrokeWidth(size);
	gr.getChildren().add(ln);
}

private void drawLine(Group gr, double x1, double y1, double x2, double y2) {
	drawLine(gr,x1,y1,x2,y2,color,2);
}

private double toDouble(StringTokenizer st) throws Exception{
	  if(st.hasMoreTokens()){
		  return Double.parseDouble(st.nextToken());
	  }else{
		  throw new InvalidCommandException("There is no parameter.");
	  }
  }

}
