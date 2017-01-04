#include <SPI.h>         // needed for Arduino versions later than 0018
#include <Ethernet.h>
#include <EthernetUdp.h>         // UDP library from: bjoern@cs.stanford.edu 12/30/2008
#include "DHT.h"


#define DHTPIN A3    // modify to the pin we connected
#define DHTTYPE DHT21   // DHT 21 (AM2301)

// Enter a MAC address and IP address for your controller below.
byte mac[] = {
};
// The IP address will be dependent on your local network:
IPAddress ip(IP);

DHT dht(DHTPIN, DHTTYPE);

int localPort = port;      // local port to listen on

int led1 = 13;
int led2 = 12;
int led3 = 11;

// buffers for receiving and sending data
char packetBuffer[UDP_TX_PACKET_MAX_SIZE];  //buffer to hold incoming packet,
char  ReplyBuffer[38];//[] = "acknowledged";       // a string to send back
char  temp[38];
String replay;
float h, t; //for temp and himity

// An EthernetUDP instance to let us send and receive packets over UDP
EthernetUDP Udp;

void setup() {


  pinMode(led1, OUTPUT);
  pinMode(led2, OUTPUT);
  pinMode(led3, OUTPUT);
  // start the Ethernet and UDP:
  Ethernet.begin(mac, ip);
  Udp.begin(localPort);
  dht.begin();
  Serial.begin(9600);
  Serial.println("Start");
 
}

void loop() {
 
  int packetSize = Udp.parsePacket();
  
  if (packetSize) { // when receive the packet the arduino
    Serial.print("Received packet of size ");
    Serial.println(packetSize); // show the size of packet and the ip which came
    Serial.print("From ");
    IPAddress remote = Udp.remoteIP();
    
    // temperature propear to send
    h = dht.readHumidity();
    t = dht.readTemperature();

    String te = "Temperature: " + String(t) + "*C Humidity: " + String(h) + " %";
    te.toCharArray(temp, 40);

    // read the packet into packetBufffer
    Udp.read(packetBuffer, UDP_TX_PACKET_MAX_SIZE);
    Serial.println("Contents:");
    Serial.println(packetBuffer);
    // if the packet say ON1 then turn on the Light
    // if is OFF1 turn off the light
    if (strcmp(packetBuffer, "ON1") == 0) {
      digitalWrite(led1, HIGH);
    } else if (strcmp(packetBuffer, "OFF1") == 0) {
      digitalWrite(led1, LOW);
    }
    if (strcmp(packetBuffer, "ON2") == 0) {
      digitalWrite(led2, HIGH);
    } else if (strcmp(packetBuffer, "OFF2") == 0) {
      digitalWrite(led2, LOW);
    }
    if (strcmp(packetBuffer, "ON3") == 0) {
      digitalWrite(led3, HIGH);
    } else if (strcmp(packetBuffer, "OFF3") == 0) {
      digitalWrite(led3, LOW);
    }
    // if is the packet temp then
    if (strcmp(packetBuffer, "Temp") == 0) {
      Serial.println("Temperature send");
      Serial.println(temp);
      //make a packet udp to send to the 
      //IP and the port which receive the packet
      //temp
      Udp.beginPacket(Udp.remoteIP(), Udp.remotePort());
      //write in packet the temper and him
      Udp.write(temp);
      //end the packet and is ready for send
      Udp.endPacket();
    } 
    // clear the char arrays for the next receive packet and send
    memset(ReplyBuffer, 0, sizeof(ReplyBuffer));
    memset(packetBuffer, 0, sizeof(packetBuffer));

  }

  delay(10);

}



