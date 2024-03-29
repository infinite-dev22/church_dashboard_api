<h3>SpringBoot API Template V1</h3>

**Description**

This project provides a robust Spring Boot web API template equipped with the following core features:

* **Secure Authentication and Authorization:** Implemented using JSON Web Tokens (JWT) to protect API endpoints and manage user access.
* **Pre-created User Functionality:** Streamlines the development process by including a basic user model and associated management endpoints.
* **Robust API Base:** Offers a well-structured foundation for building out custom API functionalities.

**API Signup Endpoint**

* URL: `http://localhost:8080/api/v1/signup`

**Getting Started**

1. **Prerequisites:**
    * Java Development Kit (JDK) version 11 or above
    * Gradle build tool

2. **Clone the Repository:**
   ```bash
   git clone git remote add origin https://github.com/infinite-dev22/spring_boot_api_template_v1.git

3. **Run the Application (using Gradle):**
   ```bash
   cd spring_boot_api_template_v1
   gradle bootRun

4. **Access the API:**
   The default port is 8080. Use an API client like Postman or curl to interact with the API endpoints. Signup functionality is available at the signup endpoint.

**Contributing**

We welcome contributions to improve and extend this template! Here's how to get involved:

1. **Review the Contribution Guidelines:** Please see the CONTRIBUTING.md file for detailed guidelines.
2. **Fork the Repository**
3. **Create a Feature Branch**
4. **Make Your Changes**
5. **Submit a Pull Request**

**License**

This project is licensed under the MIT license. See the LICENSE file in the project directory for full details.

**Additional Notes**
* Security: Review and adjust the JWT configuration as needed for your specific security requirements.
* Extensibility: Expand the template by adding new entities, API endpoints, and custom business logic.
* Documentation: Consider adding more comprehensive API documentation using tools like Swagger.
