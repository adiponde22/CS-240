#include <stdio.h>

const double PI = 3.14159;

int main(void)
{
    double userRadius = 0.0;
    printf("Enter the radius of a circle(cm): ");
    scanf("%lf", &userRadius);

    double area = PI * (userRadius * userRadius);
    double circumference = 2 * PI * userRadius;

    printf("Area: %fcm\nCircumference: %fcm\n", area, circumference);

    return 0;
}