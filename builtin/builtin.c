#include <stdio.h>
#include <stdlib.h>

const int BUFFER_LENGTH=(1<<8);
void print(char *str) { printf("%s", str); }

void println(char *str) { printf("%s\n", str); }

void printInt(int n) { printf("%d", n); }

void printlnInt(int n) { printf("%d\n", n); }

int getInt(){
    int n;
    scanf("%d",&n);
    return n;
}

char* getString(){
    char *str=(char*)malloc(sizeof(char)*BUFFER_LENGTH);
    scanf("%s",str);
    return str;
}

char* toString(int i){
     char * str = (char *) malloc(sizeof(char) * BUFFER_LENGTH);
     sprintf(str, "%d", i);
     return str;
}

