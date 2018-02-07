#include <stdio.h>
#include <stdbool.h>
int sceltaOperazione;
int num1, num2, risultato;
int cicla;
void benvenuto(int c, int *rit){
printf("Benvenuto in *Serie di funzioni* YASPL2 \n "); 
};
void termina(int c, int *rit){
printf("Grazie per aver utilizzato *Serie di funzioni* YASPL2 \n "); 
};
void somma(int p1, int p2, int *out){
*out = p1 + p2; 
};
void moltiplicazione(int p1, int p2, int *out){
int n, finale;
n = p2; 
finale = p1; 
if( n == 0 ){
*out = 0; 
}
if( n == 1 ){
*out = p1; 
}
while( n > 1 ){
p2 = finale; 
somma(p1, p2, &finale);
n = n - 1; 
}
*out = finale; 
};
void divisione(int p1, int p2, int *out){
if( p2 > 0 ){
*out = p1 / p2; 
}else{
printf("Impossibile dividere per zero! \n "); 
*out = -1; 
}
};
void potenza(int p1, int p2, int *out){
int n, finale;
n = p2; 
finale = p1; 
if( n == 0 ){
*out = 0; 
}
if( n == 1 ){
*out = p1; 
}
while( n > 1 ){
p2 = finale; 
moltiplicazione(p1, p2, &finale);
n = n - 1; 
}
*out = finale; 
};
void fib(int p1, int *out){
int f0, f1, i, ris;
f0 = 0; 
f1 = 1; 
i = 2; 
while( i <= p1 ){
ris = f1 + f0; 
f0 = f1; 
f1 = ris; 
i = i + 1; 
}
*out = ris; 
};
int main(void){
cicla = 1; 
benvenuto(cicla, &cicla);
while( cicla == 1 ){
printf("Quale funzione vuoi chiamare? \n "); 
printf("1) SOMMA \n "); 
printf("2) MOLTIPLICAZIONE \n "); 
printf("3) DIVISIONE \n "); 
printf("4) POTENZA \n "); 
printf("5) FIBONACCI \n "); 
scanf("%d", &sceltaOperazione);
if( sceltaOperazione == 1 ){
printf("Inserisci primo numero: \n "); 
scanf("%d", &num1);
printf("Inserisci secondo numero: \n "); 
scanf("%d", &num2);
somma(num1, num2, &risultato);
printf("Risultato:  \n "); 
printf("%d \n ", risultato);
}
if( sceltaOperazione == 2 ){
printf("Inserisci primo numero: \n "); 
scanf("%d", &num1);
printf("Inserisci secondo numero: \n "); 
scanf("%d", &num2);
moltiplicazione(num1, num2, &risultato);
printf("Risultato:  \n "); 
printf("%d \n ", risultato);
}
if( sceltaOperazione == 3 ){
printf("Inserisci primo numero: \n "); 
scanf("%d", &num1);
printf("Inserisci secondo numero: \n "); 
scanf("%d", &num2);
divisione(num1, num2, &risultato);
printf("Risultato:  \n "); 
printf("%d \n ", risultato);
}
if( sceltaOperazione == 4 ){
printf("Inserisci primo numero: \n "); 
scanf("%d", &num1);
printf("Inserisci secondo numero: \n "); 
scanf("%d", &num2);
potenza(num1, num2, &risultato);
printf("Risultato:  \n "); 
printf("%d \n ", risultato);
}
if( sceltaOperazione == 5 ){
printf("Inserisci un numero: \n "); 
scanf("%d", &num1);
fib(num1, &risultato);
printf("Risultato:  \n "); 
printf("%d \n ", risultato);
}
printf("Digita 1 per richiamare altre funzioni, 0 per terminare! \n "); 
scanf("%d", &cicla);
}
termina(cicla, &cicla);
return 0;
}