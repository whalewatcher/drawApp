#include <stdio.h>
#include "graphics.h"
#define RIGHT 0
#define DOWN 1
#define LEFT 2
#define UP 3
int direction = 0;
//turtle position starts from (10,10)
int currentX = 10;
int currentY = 10;

void drawLine(int x1, int x2, int x3, int x4)
{
  printf("DL %i %i %i %i\n", x1, x2, x3, x4);
}

void drawRect(int x1, int x2, int x3, int x4)
{
  printf("DR %i %i %i %i\n", x1, x2, x3, x4);
}

void drawOval(int x, int y, int width, int height)
{
  printf("DO %i %i %i %i\n",x,y,width,height);
}

void drawArc(int x, int y, int width, int height, int startAngle, int arcAngle)
{
  printf("DA %i %i %i %i %i %i\n",x,y,width,height, startAngle, arcAngle);
}

void fillRect(int x1, int x2, int x3, int x4)
{
  printf("FR %i %i %i %i\n", x1, x2, x3, x4);
}

void fillOval(int x1, int x2, int x3, int x4)
{
  printf("FO %i %i %i %i\n", x1, x2, x3, x4);
}

void drawString(char* s, int x, int y)
{
  printf("DS %s %i %i\n",s,x,y);
}

char* getColourName(colour c)
{
  char* colourName;
  switch(c)
  {
    case black : colourName = "black"; break;
    case blue : colourName = "blue"; break;
    case cyan : colourName = "cyan"; break;
    case darkgray : colourName = "darkgray"; break;
    case gray : colourName = "gray"; break;
    case green : colourName = "green"; break;
    case lightgray : colourName = "lightgray"; break;
    case magenta : colourName = "magenta"; break;
    case orange : colourName = "orange"; break;
    case pink : colourName = "pink"; break;
    case red : colourName = "red"; break;
    case white : colourName = "white"; break;
    case yellow : colourName = "yellow"; break;
  }
  return colourName;
}

void setColour(colour c)
{
  printf("SC %s\n", getColourName(c));
}

void changeDimension(int x, int y)
{
	printf("CD %i %i\n",x,y);
}

void displayImage(int x, int y, int width, int height, char* fileName)
{
	printf("DI %i %i %i %i %s\n",x,y,width,height,fileName);
}

void setColourGradient(int x1, int y1, int x2, int y2, int isProportional, cycleMethod cycle, colour c1, colour c2, int offset)
{
	char* colourName1 = getColourName(c1);
	char* colourName2 = getColourName(c1);
	char* cm;
  switch(cycle)
  {
	case NO_CYCLE : cm = "NO_CYCLE";break;
	case REFLECT : cm = "REFLECT";break;
	case REPEAT : cm = "REPEAT";break;
  }
	printf("CG %i %i %i %i %i %s %s %s %i\n",x1,y1,x2,y2,isProportional,cm,colourName1,colourName2,offset);
}

void drawPolygon(int *points, int size)
{
	int i = 0;
	printf("DP");
	for(i=0;i<size;i++){
		printf(" %i",points[i]);
	}
	printf("\n");
}

void fillPolygon(int *points, int size)
{
	int i = 0;
	printf("FP");
	for(i=0;i<size;i++){
		printf(" %i",points[i]);
	}
	printf("\n");
}

//for turtle graphics
void moveForward(int howMuch)
{
	switch(direction){
		case LEFT : drawTurtleLine(currentX-howMuch,currentY,currentX,currentY); currentX = currentX - howMuch; break;
		case RIGHT : drawTurtleLine(currentX,currentY,currentX+howMuch,currentY); currentX = currentX + howMuch; break;
		case UP : drawTurtleLine(currentX,currentY-howMuch,currentX,currentY); currentY = currentY - howMuch; break;
		case DOWN : drawTurtleLine(currentX,currentY,currentX,currentY+howMuch); currentY = currentY + howMuch; break;
	}
}

void turnLeft()
{
	direction--;
	if(direction==-1)
		direction = 3;
}

void turnRight()
{
	direction++;
	if(direction==4)
		direction = 0;
}

void drawTurtleLine(int x1, int x2, int x3, int x4)
{
  printf("TL %i %i %i %i\n", x1, x2, x3, x4);
}

void setTurtleColour(colour c)
{
  printf("TC %s\n",getColourName(c));
}

void setTurtleSize(int size)
{
  printf("TS %i\n",size);
}
