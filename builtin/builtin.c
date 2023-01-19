#include <stdio.h>
#include <stdlib.h>
#include <string.h>


const int BUFFER_LENGTH = (1 << 8);

void print(char *str) { printf("%s", str); }

void println(char *str) { printf("%s\n", str); }

void printInt(int n) { printf("%d", n); }

void printlnInt(int n) { printf("%d\n", n); }

int getInt() {
    int n;
    scanf("%d", &n);
    return n;
}

char *getString() {
    char *str = (char *) malloc(sizeof(char) * BUFFER_LENGTH);
    scanf("%s", str);
    return str;
}

char *toString(int i) {
    char *str = (char *) malloc(sizeof(char) * BUFFER_LENGTH);
    sprintf(str, "%d", i);
    return str;
}

char ___str_eq(char *s1, char *s2) { return strcmp(s1, s2) == 0; }

char ___str_ne(char *s1, char *s2) { return strcmp(s1, s2) != 0; }

char ___str_slt(char *s1, char *s2) { return strcmp(s1, s2) < 0; }

char ___str_sle(char *s1, char *s2) { return strcmp(s1, s2) <= 0; }

char ___str_sgt(char *s1, char *s2) { return strcmp(s1, s2) > 0; }

char ___str_sge(char *s1, char *s2) { return strcmp(s1, s2) >= 0; }

char *___str_add(char *s1, char *s2) {
    char *str = (char *) malloc(sizeof(char) * (strlen(s1) + strlen(s2) + 1));
    strcpy(str, s1);
    strcat(str, s2);
    return str;
}

char *__string_substring(int left, int right,char *str) {
    int len = right - left + 1;
    char *substr = (char *) malloc(sizeof(char) * len);
    memcpy(substr, str + left, len);
    substr[len - 1] = '\0';
    return substr;
}

int __string_length(char *str) {
    return strlen(str);
}

int __string_parseInt(char *str) {
    int val;
    sscanf(str, "%d", &val);
    return val;
}

int __string_ord(int num, char *str) {
    return (int) str[num];
}

char *__malloc(int size) {
    return (char *) malloc(size);
}