// ordering.c
#include "ordering.h"
#include <string.h>

int compareByID(const void *a, const void *b) {
  return ((Student *)a)->id - ((Student *)b)->id;
}

int compareByName(const void *a, const void *b) {
  return strcmp(((Student *)a)->lastName, ((Student *)b)->lastName);
}

int compareByExam(const void *a, const void *b) {
  return ((Student *)b)->exam - ((Student *)a)->exam;
}

int compareByTotal(const void *a, const void *b) {
  return (int)(((Student *)b)->total - ((Student *)a)->total);
}
