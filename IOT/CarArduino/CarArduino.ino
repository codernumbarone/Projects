#include <ESP8266WiFi.h>
#include <FirebaseArduino.h>
// Set these to run example.
#define FIREBASE_HOST "iotfirebaseproject-de118.firebaseio.com"
#define FIREBASE_AUTH "MH1gHbzN89xfWYsPPA2ddAZQAWw7VSgqkHboArCT"
#define WIFI_SSID "Z"
#define WIFI_PASSWORD "zozo1234"

#define LEFT_SENSORPIN 4
#define CENTER_SENSORPIN 0
#define RIGHT_SENSORPIN 2

#define LINE HIGH
#define NOLINE LOW
enum {GO_AHEAD, GO_LEFT, GO_RIGHT, STOP, GO_POWERLEFT, GO_POWERRIGHT};
int value = 0;
int value1 = 0;
void setup() {
  Serial.begin(9600);
  pinMode(13, OUTPUT);
  pinMode(12, OUTPUT);
    pinMode(14, OUTPUT);
  pinMode(15, OUTPUT);
  pinMode(2, INPUT);
  pinMode(0, INPUT);
  pinMode(4, INPUT);

  WiFi.begin(WIFI_SSID, WIFI_PASSWORD);
  Serial.print("connecting");
  while (WiFi.status() != WL_CONNECTED) {
    Serial.print(".");
    delay(500);
  Serial.println();
  Serial.print("connected: ");
  Serial.println(WiFi.localIP());
  Firebase.begin(FIREBASE_HOST, FIREBASE_AUTH);
 
  Firebase.set("Path", 0);
  if (Firebase.failed()) {
    Serial.println("failed to connect");
  } else {
    Serial.println("FireBase Connected");

  }
}

}
void loop() {
  

  int n = Firebase.getInt("Path");
  String one ="/C1I1/";
  
  if(n==1){

    int a1 = Firebase.getInt("Size1");
    Serial.println(a1);
    for(int i=0;i<a1;i++){
      one+=i;
      String s1 = Firebase.getString(one);
      Serial.println(s1);
      Serial.println(one);

      if(s1=="Forward"&&i==a1-1||a1==1&&s1=="Forward"){
      runForward();
           Firebase.set("Path", 0);
      }else if(s1=="Forward"){
              runForward();
      }
      if(s1=="Right"&&i==a1-1||a1==1&&s1=="Right"){
      runRight();
           Firebase.set("Path", 0);
      }else if(s1=="Right"){
       
        runRight();
      }
    if(s1=="Left"&&i==a1-1||a1==1&&s1=="Left"){
      runLeft();

           Firebase.set("Path", 0);
       }else if(s1=="Left"){
        runLeft();
       }
            one="/C1I1/";
    }
  }

  String two ="/C1I2/";

   if(n==2){

    int a2 = Firebase.getInt("Size2");
    Serial.println(a2);
    for(int i=0;i<a2;i++){
      two+=i;
      String s2 = Firebase.getString(two);
      Serial.println(s2);
      Serial.println(two);

      if(s2=="Forward"&&i==a2-1||a2==1&&s2=="Forward"){
      runForward();
           Firebase.set("Path", 0);
      }else if(s2=="Forward"){
              runForward();
      }
      if(s2=="Right"&&i==a2-1||a2==1&&s2=="Right"){
      runRight();
           Firebase.set("Path", 0);
      }else if(s2=="Right"){
       
        runRight();
      }
    if(s2=="Left"&&i==a2-1||a2==1&&s2=="Left"){
      runLeft();

           Firebase.set("Path", 0);
       }else if(s2=="Left"){
        runLeft();
       }
            two="/C1I2/";
    }
  }
   String three="/C1I3/";

      if(n==3){

    int a3 = Firebase.getInt("Size3");
    Serial.println(a3);
    for(int i=0;i<a3;i++){
      three+=i;
      String s3 = Firebase.getString(three);
      Serial.println(s3);
      Serial.println(three);

      if(s3=="Forward"&&i==a3-1||a3==1&&s3=="Forward"){
      runForward();
           Firebase.set("Path", 0);
      }else if(s3=="Forward"){
              runForward();
      }
      if(s3=="Right"&&i==a3-1||a3==1&&s3=="Right"){
      runRight();
           Firebase.set("Path", 0);
      }else if(s3=="Right"){
       
        runRight();
      }
    if(s3=="Left"&&i==a3-1||a3==1&&s3=="Left"){
      runLeft();

           Firebase.set("Path", 0);
       }else if(s3=="Left"){
        runLeft();
       }
            three="/C1I3/";
    }
  }
  String four ="/C1I4/";

      if(n==4){

    int a4 = Firebase.getInt("Size4");
    Serial.println(a4);
    for(int i=0;i<a4;i++){
      four+=i;
      String s4 = Firebase.getString(four);
      Serial.println(s4);
      Serial.println(four);

      if(s4=="Forward"&&i==a4-1||a4==1&&s4=="Forward"){
      runForward();
           Firebase.set("Path", 0);
      }else if(s4=="Forward"){
              runForward();
      }
      if(s4=="Right"&&i==a4-1||a4==1&&s4=="Right"){
      runRight();
           Firebase.set("Path", 0);
      }else if(s4=="Right"){
       
        runRight();
      }
    if(s4=="Left"&&i==a4-1||a4==1&&s4=="Left"){
      runLeft();

           Firebase.set("Path", 0);
       }else if(s4=="Left"){
        runLeft();
       }
            four="/C1I4/";
    }
  }
  String five ="/C1I5/";

     if(n==5){

    int a5 = Firebase.getInt("Size5");
    Serial.println(a5);
    for(int i=0;i<a5;i++){
      one+=i;
      String s5 = Firebase.getString(five);
      Serial.println(s5);
      Serial.println(five);

      if(s5=="Forward"&&i==a5-1||a5==1&&s5=="Forward"){
      runForward();
           Firebase.set("Path", 0);
      }else if(s5=="Forward"){
              runForward();
      }
      if(s5=="Right"&&i==a5-1||a5==1&&s5=="Right"){
      runRight();
           Firebase.set("Path", 0);
      }else if(s5=="Right"){
       
        runRight();
      }
    if(s5=="Left"&&i==a5-1||a5==1&&s5=="Left"){
      runLeft();

           Firebase.set("Path", 0);
       }else if(s5=="Left"){
        runLeft();
       }
            five="/C1I5/";
    }
  }
    int z = Firebase.getInt("Path2");

   String six ="/C1I6/";

     if(n==6){

    int a6 = Firebase.getInt("Size6");
    Serial.println(a6);
    for(int i=0;i<a6;i++){
      one+=i;
      String s6 = Firebase.getString(six);
      Serial.println(s6);
      Serial.println(six);

      if(s6=="Forward"&&i==a6-1||a6==1&&s6=="Forward"){
      runForward();
           Firebase.set("Path", 0);
      }else if(s6=="Forward"){
              runForward();
      }
      if(s6=="Right"&&i==a6-1||a6==1&&s6=="Right"){
      runRight();
           Firebase.set("Path", 0);
      }else if(s6=="Right"){
       
        runRight();
      }
    if(s6=="Left"&&i==a6-1||a6==1&&s6=="Left"){
      runLeft();

           Firebase.set("Path", 0);
       }else if(s6=="Left"){
        runLeft();
       }
            six="/C1I6/";
    }
  }
  String seven ="/C2I1/";
  
  if(n==7){

    int a7 = Firebase.getInt("Size7");
    Serial.println(a7);
    for(int i=0;i<a7;i++){
      seven+=i;
      String s7 = Firebase.getString(seven);
      Serial.println(s7);
      Serial.println(seven);

      if(s7=="Forward"&&i==a7-1||a7==1&&s7=="Forward"){
      runForward();
           Firebase.set("Path", 0);
      }else if(s7=="Forward"){
              runForward();
      }
      if(s7=="Right"&&i==a7-1||a7==1&&s7=="Right"){
      runRight();
           Firebase.set("Path", 0);
      }else if(s7=="Right"){
       
        runRight();
      }
    if(s7=="Left"&&i==a7-1||a7==1&&s7=="Left"){
      runLeft();

           Firebase.set("Path", 0);
       }else if(s7=="Left"){
        runLeft();
       }
            seven="/C2I1/";
    }
  }

  String eight ="/C2I2/";

   if(n==8){

    int a8 = Firebase.getInt("Size8");
    Serial.println(a8);
    for(int i=0;i<a8;i++){
      eight+=i;
      String s8 = Firebase.getString(eight);
      Serial.println(s8);
      Serial.println(eight);

      if(s8=="Forward"&&i==a8-1||a8==1&&s8=="Forward"){
      runForward();
           Firebase.set("Path", 0);
      }else if(s8=="Forward"){
              runForward();
      }
      if(s8=="Right"&&i==a8-1||a8==1&&s8=="Right"){
      runRight();
           Firebase.set("Path", 0);
      }else if(s8=="Right"){
       
        runRight();
      }
    if(s8=="Left"&&i==a8-1||a8==1&&s8=="Left"){
      runLeft();

           Firebase.set("Path", 0);
       }else if(s8=="Left"){
        runLeft();
       }
            eight="/C2I2/";
    }
  }
   String nine="/C2I3/";

      if(n==9){

    int a9 = Firebase.getInt("Size9");
    Serial.println(a9);
    for(int i=0;i<a9;i++){
      nine+=i;
      String s9 = Firebase.getString(nine);
      Serial.println(s9);
      Serial.println(nine);

      if(s9=="Forward"&&i==a9-1||a9==1&&s9=="Forward"){
      runForward();
           Firebase.set("Path", 0);
      }else if(s9=="Forward"){
              runForward();
      }
      if(s9=="Right"&&i==a9-1||a9==1&&s9=="Right"){
      runRight();
           Firebase.set("Path", 0);
      }else if(s9=="Right"){
       
        runRight();
      }
    if(s9=="Left"&&i==a9-1||a9==1&&s9=="Left"){
      runLeft();

           Firebase.set("Path", 0);
       }else if(s9=="Left"){
        runLeft();
       }
            nine="/C3I3/";
    }
  }
  String ten ="/C2I4/";

      if(n==10){

    int a10 = Firebase.getInt("Size10");
    Serial.println(a10);
    for(int i=0;i<a10;i++){
      ten+=i;
      String s10 = Firebase.getString(ten);
      Serial.println(s10);
      Serial.println(ten);

      if(s10=="Forward"&&i==a10-1||a10==1&&s10=="Forward"){
      runForward();
           Firebase.set("Path", 0);
      }else if(s10=="Forward"){
              runForward();
      }
      if(s10=="Right"&&i==a10-1||a10==1&&s10=="Right"){
      runRight();
           Firebase.set("Path", 0);
      }else if(s10=="Right"){
       
        runRight();
      }
    if(s10=="Left"&&i==a10-1||a10==1&&s10=="Left"){
      runLeft();

           Firebase.set("Path", 0);
       }else if(s10=="Left"){
        runLeft();
       }
            ten="/C2I4/";
    }
  }
  String eleven ="/C2I5/";

     if(n==11){

    int a11 = Firebase.getInt("Size11");
    Serial.println(a11);
    for(int i=0;i<a11;i++){
      eleven+=i;
      String s11 = Firebase.getString(eleven);
      Serial.println(s11);
      Serial.println(eleven);

      if(s11=="Forward"&&i==a11-1||a11==1&&s11=="Forward"){
      runForward();
           Firebase.set("Path", 0);
      }else if(s11=="Forward"){
              runForward();
      }
      if(s11=="Right"&&i==a11-1||a11==1&&s11=="Right"){
      runRight();
           Firebase.set("Path", 0);
      }else if(s11=="Right"){
       
        runRight();
      }
    if(s11=="Left"&&i==a11-1||a11==1&&s11=="Left"){
      runLeft();

           Firebase.set("Path", 0);
       }else if(s11=="Left"){
        runLeft();
       }
            eleven="/C2I5/";
    }
  }
   String twelve ="/C2I6/";

     if(n==12){

    int a12 = Firebase.getInt("Size12");
    Serial.println(a12);
    for(int i=0;i<a12;i++){
      twelve+=i;
      String s12 = Firebase.getString(twelve);
      Serial.println(s12);
      Serial.println(twelve);

      if(s12=="Forward"&&i==a12-1||a12==1&&s12=="Forward"){
      runForward();
           Firebase.set("Path", 0);
      }else if(s12=="Forward"){
              runForward();
      }
      if(s12=="Right"&&i==a12-1||a12==1&&s12=="Right"){
      runRight();
           Firebase.set("Path", 0);
      }else if(s12=="Right"){
       
        runRight();
      }
    if(s12=="Left"&&i==a12-1||a12==1&&s12=="Left"){
      runLeft();

           Firebase.set("Path", 0);
       }else if(s12=="Left"){
        runLeft();
       }
            twelve="/C2I6/";
    }
  }
  if(n==0){
    digitalWrite(13, LOW);
    digitalWrite(12, LOW);
    }

  byte leftSensor=digitalRead(LEFT_SENSORPIN);
  byte centerSensor=digitalRead(CENTER_SENSORPIN);
  byte rightSensor=digitalRead(RIGHT_SENSORPIN);
  
//  Serial.println("Left Sensor is");
//  Serial.println(leftSensor);
//  Serial.println("Right Sensor is");
//  Serial.println(rightSensor);
//  Serial.println("Centor Sensor is");
//  Serial.println(centerSensor);
     
//   if (leftSensor==NOLINE && centerSensor==NOLINE && rightSensor==NOLINE){
//      goDirection= STOP;
//      Firebase.set("Direction", 0);
//   }
    if (leftSensor==0 && centerSensor==0 && rightSensor==0){
    digitalWrite(13, LOW);
    digitalWrite(12, LOW);
    }

  
}


void runForward(){
digitalWrite(13,HIGH);
digitalWrite(12,HIGH);
delay(680);
digitalWrite(13,LOW);
digitalWrite(12,LOW);
delay(180);
//  Firebase.set("Path", 0);

}

void runRight(){
digitalWrite(13,HIGH);
digitalWrite(12,LOW);
//delay(1120);
delay(1500);
digitalWrite(13,HIGH);
digitalWrite(12,HIGH);
delay(180);
digitalWrite(13,LOW);
digitalWrite(12,LOW);
delay(180);
//      Firebase.set("Path", 0);

}

void runLeft(){
digitalWrite(13,LOW);
digitalWrite(12,HIGH);
delay(3000);
digitalWrite(13,HIGH);
digitalWrite(12,HIGH);
delay(100);
digitalWrite(13,LOW);
digitalWrite(12,LOW);
delay(180);
//      Firebase.set("Path", 0);


}
