#include <stdio.h>
#include <stdbool.h>
int num1, num2, result;
double numD1, numD2, resultD;
int operation, type;
bool boolValue, exit;
void HelloCalculator(int a, bool *toReturn){
printf("Benvenuto nella prima calcolatrice scritta in YASPL2! \n "); 
};
void IntOperation(int x, int y, int op, int *z){
if( op == 2 ){
*z = x + y; 
}
if( op == 3 ){
*z = x - y; 
}
if( op == 4 ){
*z = x * y; 
}
if( op == 5 ){
*z = x / y; 
}
if( op > 5 ){
printf("OPERAZIONE ERRATA \n "); 
*z = 0; 
}
};
void DoubleOperation(double x, double y, int op, double *z){
if( op == 2 ){
*z = x + y; 
}
if( op == 3 ){
*z = x - y; 
}
if( op == 4 ){
*z = x * y; 
}
if( op == 5 ){
*z = x / y; 
}
if( op > 5 ){
printf("OPERAZIONE ERRATA \n "); 
*z = 0; 
}
};
int main(void){
HelloCalculator(num1, &boolValue);
while( true ){
printf("MENU UTENTE \n "); 
printf("Premi 2 per ADDIZIONE \n "); 
printf("Premi 3 per SOTTRAZIONE \n "); 
printf("Premi 4 per MOLTIPLICAZIONE \n "); 
printf("Premi 5 per DIVISIONE \n "); 
scanf("%d", &operation);
printf("SELEZIONARE IL TIPO DEI NUMERI \n "); 
printf("Premi 6 per INT \n "); 
printf("Premi 7 per DOUBLE \n "); 
scanf("%d", &type);
if( type == 6 ){
printf("Inserisci primo numero (INT) \n "); 
scanf("%d", &num1);
printf("Inserisci secondo numero (INT) \n "); 
scanf("%d", &num2);
IntOperation(num1, num2, operation, &result);
printf("RISULTATO:  \n "); 
}else{
printf("Inserisci primo numero (DOUBLE) \n "); 
scanf("%lf", &numD1);
printf("Inserisci secondo numero (DOUBLE) \n "); 
scanf("%lf", &numD2);
DoubleOperation(numD1, numD2, operation, &resultD);
printf("RISULTATO:  \n "); 
}
printf("Premi 0 per CONTINUARE \n "); 
printf("Premi 1 per uscire \n "); 
}
return 0;
}