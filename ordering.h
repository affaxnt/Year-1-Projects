#ifndef ORDERING_H
#define ORDERING_H

#include "student.h"

// Function prototypes for sorting
int compareByID(const void *a, const void *b);
int compareByName(const void *a, const void *b);
int compareByExam(const void *a, const void *b);
int compareByTotal(const void *a, const void *b);

#endif //ORDERING_H
