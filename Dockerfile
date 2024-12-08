# Start with a base image that includes OpenJDK
FROM openjdk:17-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the application JAR file to the container
# Replace 'your-springboot-app.jar' with your actual JAR file name
COPY target/cab-booking-backend-0.0.1-SNAPSHOT.jar cab-booking-backend-0.0.1-SNAPSHOT.jar

# Expose the port your Spring Boot application runs on
EXPOSE 8080

# Pass the environment variables to the container
# These values can also be overridden when running the container
ENV SERVER_PORT=8080 \
    API_SUCCESS="New Employee Added Successfully" \
    API_DELETED="Employee Details Deleted" \
    API_UPDATED="Employee Details Updated" \
    EMPLOYEE_SALARY_NOT_PRESENT="Provide Employee Salary" \
    SERVICE_EMP_NOT_EXISTS="Employee not found" \
    SERVICE_CITY_EXISTS="CITY vundi Seetha" \
    SERVICE_CITY_DOESNT_EXISTS="CITY ledu Seetha" \
    SERVICE_NO_CITIES_FOUND="Ottu seetha okka city kooda ledu" \
    SERVICE_NO_EMPS_FOUND="No Employees found" \
    SERVICE_ALREADY_EXISTS="Employee Already exist" \
    GENERAL_EXCEPTION="Some Exception occurred"

# Set the command to run the application
CMD ["java", "-jar", "cab-booking-backend-0.0.1-SNAPSHOT.jar"]
