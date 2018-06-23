  /* 
  *  
  * (C) 2018 -  Emanuele Paiano 
  *  SpringDuino sketch test 
  *  
  * istruction sintax:
 *  <opcode>, <param1>, <param2>
 *  field delimiter char: ","
 *  
 *  example: 0,13,0 -> digitalRead(13)   --> read data from pin 13
  *         1,13,1 -> digitalWrite(13, 1)  --> writing HIGH on output pin 13 (turn on Arduino led)
  *         1,13,0 -> digitalWrite(13, 0)  --> writing LOW on output pin 13 (turn off Arduino led)
  *  
  * ----------------------------------------------------------
  *  opcode | param1   |  param2  |      action
  * ----------------------------------------------------------
  *     0   |    X     |    0     |   digitalRead(X)
  * ----------------------------------------------------------
  *     1   |    X     |    Y     |   digitalWrite(X,Y)
  * ----------------------------------------------------------
  *     2   |    X     |    0     |   analogRead(X)
  * ----------------------------------------------------------
  *     3   |    X     |    Y     |   analogWrite(X,Y)
  * ----------------------------------------------------------
  *     4   |    X     |    0     |   delay(X)
  * ----------------------------------------------------------
  *
  * ----------------------------------------------------------
  *     5    |   X     |    Y     |  dummyFunction()
  * ----------------------------------------------------------
*/


void setup() {
  /* pinout setup */
  pinMode(13, OUTPUT);

  
  /* init serial Link */
  Serial.begin(19200); 
  
  
}

void loop() {
  while (Serial.available())
    {
        String bufferInput=Serial.readStringUntil('\n');
        int delim[2], j=0;        
        for (int i = 0; i < bufferInput.length(); i++) {
          if (bufferInput.substring(i, i+1) == ",") {
            delim[j]=i;
            j++;
            if (j==2) break;
          }
        }
        int opcode=bufferInput.substring(0, delim[0]).toInt();
        long param1=bufferInput.substring(delim[0]+1, delim[1]).toInt();
        long param2=bufferInput.substring(delim[1]+1).toInt();
        
        cmdDispatch(opcode, param1, param2);
        
    }
}


/* Comands List */
void cmdDispatch(int opcode, long param1, long param2)
{
 String output="";

  switch(opcode)
  {
   case 0:
          Serial.println(digitalRead(param1));
          break;
   case 1:
          digitalWrite(param1, param2);
          break;
   case 2:
          Serial.println(analogRead(param1));
          break;
   case 3:
          analogWrite(param1, param2);
          break;
   case 4:
          delay(param1);
          break;

   /* Custom functions */
   case 5:
          dummyFunction();
          break;

   

  }
 
}



/* Custom functions */

void dummyFunction()
{
 Serial.println("Dummy function");
}



