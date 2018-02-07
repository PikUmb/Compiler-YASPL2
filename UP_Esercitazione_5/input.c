#include <stdio.h>
#include <stdbool.h>
int scelta;
int cicla;
void controllaCicla(int aa, int kk, int b, int *outscelta){
if( b == 1 ){
*outscelta = aa + kk; 
}else{
*outscelta = 0; 
}
};
void controll(int aa, int *o){
*o = 2000; 
};
int main(void){
scelta = -1; 
controllaCicla(scelta, scelta, scelta, &cicla);
printf("Ciao Carmine, sono l'eseguibile Web! \n "); 
printf("Risultato prima funzione:  \n "); 
printf("%d \n ", cicla);
controll(scelta, &cicla);
printf("Risultato seconda funzione:  \n "); 
printf("%d \n ", cicla);
printf("Compilato con Emscripten \n "); 
return 0;
}