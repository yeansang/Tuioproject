#include <Wire.h> //I2C Arduino Library
#include <SoftwareSerial.h>

#define address 0x1E //0011110b, I2C 7bit address of HMC5883


SoftwareSerial mySerial(10, 11); // RX, TX

//모터 제어용 핀 세팅
int EL = 5;  
int ML = 4; 
int ER = 6;                      
int MR = 7;                        
//---

int value = 0;//모터 속도 제어

//시리얼에서 긁어올 변수
long byteIn = 0;
String input;

int in = 0;

void setup() 
{ 
 mySerial.begin(9600);
  pinMode(ML, OUTPUT);   
  pinMode(MR, OUTPUT); 

/*
  //--지자기 센서 세팅
   Wire.begin();
   Wire.beginTransmission(address); //open communication with HMC5883
   Wire.write(0x02); //select mode register
   Wire.write(0x00); //continuous measurement mode
   Wire.endTransmission();
   //--
*/
   

 
} 
 
void loop() 
{ 
  byteIn = 0;

  
  int mottime = 0;
  //--시리얼에서 정보 받기
  int ang = 0;
  int spd = 0;

 if(mySerial.available()){
  mySerial.println("Hello, world?");
  input = mySerial.readString();
  byteIn = input.toInt();
  
  
   }
   //---
 // Serial.println(degCheck());

  
 // for(int i = 0;input.length() > i;i++){
   // in=byteIn%10;
    //byteIn/=10;
    //Serial.println(in);
  switch(byteIn)
  {
    case 0 :
  analogWrite(EL, 0);
  analogWrite(ER, 0);
  break;
    case 1 : 
    go(1000, 255);
  break;
    case 2 :
    back(1000, 255);
  break;
    case 3 :
    turnRight(490);
    go(500, 255);
  break;
    case 4 :
    turnLeft(490);
    go(500, 255);
  break;
   default :
  analogWrite(EL, 0);
  analogWrite(ER, 0);
  break;
  
  }
 // }

  delay(5);
}

void go(int timeSet, int sped)
{
  value = sped;
  digitalWrite(ML, HIGH);
  digitalWrite(MR, HIGH);
  analogWrite(EL, value);
  analogWrite(ER, value);
  delay(timeSet);
  analogWrite(EL, 0);
  analogWrite(ER, 0);
}

void back(int timeSet, int sped)
{
  value = sped;
  digitalWrite(ML, LOW);
  digitalWrite(MR, LOW);
  analogWrite(EL, value);
  analogWrite(ER, value);
  delay(timeSet);
  analogWrite(EL, 0);
  analogWrite(ER, 0);
}

void turnRight(int timeSet)
{
  value = 255;
  digitalWrite(ML, HIGH);
  digitalWrite(MR, LOW);
  analogWrite(EL, value);
  analogWrite(ER, value);
  delay(timeSet);
  analogWrite(EL, 0);
  analogWrite(ER, 0);
  
}

void turnLeft(int timeSet)
{
  value = 255;
  digitalWrite(ML, LOW);
  digitalWrite(MR, HIGH);
  analogWrite(EL, value);
  analogWrite(ER, value);
  delay(timeSet);
  analogWrite(EL, 0);
  analogWrite(ER, 0);
}
/*
float degCheck() //각도 출력
{
 int x,y,z; //triple axis data
 //Tell the HMC5883 where to begin reading data
  Wire.beginTransmission(address);
 Wire.write(0x03); //select register 3, X MSB register
 Wire.endTransmission();
 //Read data from each axis, 2 registers per axis
 Wire.requestFrom(address, 6);
 if(6<=Wire.available()){
 x = Wire.read()<<8; //X msb
 x |= Wire.read(); //X lsb
 y = Wire.read()<<8; //Y msb
 y |= Wire.read(); //Y lsb
 z = Wire.read()<<8; //z msb
 z |= Wire.read(); //z lsb
 }
 return (atan2(y, x) * 180)/ PI ;
}

*/

