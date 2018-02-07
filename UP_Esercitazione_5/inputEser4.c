#include <stdio.h>
#include <stdbool.h>
int sceltaOperazione, tipoOperazione;
int numI1, numI2, risultatoInt;
double numD1, numD2, risultatoD;
int cicla;
void benvenuto(int c, int *rit){
printf("Benvenuto nella calcolatrice YASPL2 \n "); 
};
void termina(int c, int *rit){
printf("Grazie per aver utilizzato la calcolatrice YASPL2 \n "); 
};
void operazioneInt(int p1, int p2, int operazione, int *out){
if( operazione == 2 ){
*out = p1 + p2; 
}
if( operazione == 3 ){
*out = p1 - p2; 
}
if( operazione == 4 ){
*out = p1 * p2; 
}
if( operazione == 5 ){
*out = p1 / p2; 
}
};
void operazioneDouble(double p1, double p2, int operazione, double *out){
if( operazione == 2 ){
*out = p1 + p2; 
}
if( operazione == 3 ){
*out = p1 - p2; 
}
if( operazione == 4 ){
*out = p1 * p2; 
}
if( operazione == 5 ){
*out = p1 / p2; 
}
};
int main(void){
cicla = 1; 
benvenuto(cicla, &cicla);
while( cicla == 1 ){
printf("Menu utente \n "); 
printf("Scegli l’operazione da effettuare, digita:  \n "); 
printf("2) ADDIZIONE \n "); 
printf("3) SOTTRAZIONE \n "); 
printf("4) MOLTIPLICAZIONE \n "); 
printf("5) DIVISIONE \n "); 
scanf("%d", &sceltaOperazione);
if( sceltaOperazione > 5 ){
printf("Operazione non riconosciuta \n "); 
}
printf("Scegli il tipo dei numeri: \n "); 
printf("6) INT \n "); 
printf("7) DOUBLE \n "); 
scanf("%d", &tipoOperazione);
if( tipoOperazione > 7 ){
printf("Tipo non riconosciuto \n "); 
}
if( tipoOperazione == 6 ){
printf("Inserisci primo numero \n "); 
scanf("%d", &numI1);
printf("Inserisci secondo numero \n "); 
scanf("%d", &numI2);
operazioneInt(numI1, numI2, sceltaOperazione, &risultatoInt);
printf("Risultato operazione: \n "); 
printf("%d \n ", risultatoInt);
}else{
printf("Inserisci primo numero \n "); 
scanf("%lf", &numD1);
printf("Inserisci secondo numero \n "); 
scanf("%lf", &numD2);
operazioneDouble(numD1, numD2, sceltaOperazione, &risultatoD);
printf("Risultato operazione: \n "); 
printf("%lf \n ", risultatoD);
}
printf("Digita 1 per effettuare altre operazioni, 0 per terminare! \n "); 
scanf("%d", &cicla);
}
termina(cicla, &cicla);
return 0;
}