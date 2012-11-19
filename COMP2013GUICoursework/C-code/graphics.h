enum colour {black,blue,cyan,darkgray,gray,green,lightgray,magenta,orange,pink,red,white,yellow};
typedef enum colour colour;
enum cycleMethod {NO_CYCLE,REFLECT,REPEAT};
typedef enum cycleMethod cycleMethod;
int direction;
int currentX;
int currentY;

void drawLine(int,int,int,int);
void drawRect(int,int,int,int);
void drawOval(int,int,int,int);
void drawArc(int,int,int,int,int,int);
void fillRect(int,int,int,int);
void drawString(char*,int,int);
char* getColourName(colour);
void setColour(colour);
void changeDimension(int,int);
void displayImage(int,int,int,int,char*);
void setColourGradient(int,int,int,int,int,cycleMethod,colour,colour,int);
void fillOval(int,int,int,int);
void drawPolygon(int*,int);
void fillPolygon(int*,int);

//for Turtle Graphics
void moveForward(int);
void turnLeft();
void turnRight();
void drawTurtleLine(int,int,int,int);
void setTurtleColour(colour);
void setTurtleSize(int);
