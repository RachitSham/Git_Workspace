# Step 1: Use official Tomcat base image (e.g., Tomcat 10 with JDK 21)
FROM tomcat:10.1-jdk17

# Step 2: Clean default webapps
RUN rm -rf /usr/local/tomcat/webapps/*

# Step 3: Copy WAR to Tomcat’s webapps folder
COPY target/WishMessageApp1.war /usr/local/tomcat/webapps/WishMessageApp1.war

# Step 4: Expose port (optional, if needed externally)
EXPOSE 8080

# Step 5: Start Tomcat
CMD ["catalina.sh", "run"]
