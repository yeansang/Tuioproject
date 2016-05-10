long byteIn = 0;
String input;

void setup() {
  // put your setup code here, to run once:
 Serial.begin(9600);
 pinMode(13, OUTPUT);
}

void loop() {
  int ang = 0;
  int spd = 0;
 if(Serial.available() >0){
  input = Serial.readString();
  byteIn = (long)input.toFloat();
  Serial.println(byteIn);
  ang = (byteIn%1000)-100;
  spd = (byteIn/1000);
  Serial.println(spd);
  Serial.println(ang);
   }
  }


