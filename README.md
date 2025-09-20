# Hybrid Selenium Automation Framework With Jenkins,Docker,Selenium-Grid integration.

**Hybrid Java Selenium automation framework for scalable CI/CD pipelines with Jenkins,Docker and Selenium-Grid integration.**

🚀 Key Highlights  
- Built with **Java + Selenium WebDriver + TestNG + Maven+log4j+Extent Reports 
- **Dockerized Selenium Grid** for parallel & distributed execution  
- **Jenkins integration** for CI/CD automation  
- Extensible **hybrid framework** (Data-driven + Keyword-driven + Page Object Model)  
- Generates detailed **logs, reports, and screenshots** 

✨ Features

✅ Hybrid framework (POM + Data-Driven + Keyword-Driven)

✅ Cross-browser execution (Chrome, Firefox, etc.)

✅ Parallel & distributed execution via Selenium Grid (Docker Compose)

✅ CI/CD ready with Jenkins pipeline support

✅ TestNG XML suite execution (grouping, parallel runs)

✅ Rich HTML test reports (Extent) + logs (log4j) + screenshots


    ┌──────────────┐
    │ TestNG XML   │  → Controls test execution
    └─────┬────────┘
          │
    ┌─────▼────────┐
    │ Test Classes │  → Call Page Objects & Keywords
    └─────┬────────┘
          │
    ┌─────▼────────┐
    │ Page Objects │  → Encapsulate UI locators & actions
    └─────┬────────┘
          │
    ┌─────▼────────┐
    │ Keywords     │  → Reusable test actions (login, search, etc.)
    └─────┬────────┘
          │
    ┌─────▼────────┐
    │ Test Data    │  → Excel / external data sources
    └──────────────┘

# ⚙️ Prerequisites

Make sure you have the following installed:

Java 8+ / 11

Maven 3.8+

Docker & Docker Compose

Jenkins (if running in CI/CD)

# Setup & Installation
1. Clone repository
* git clone https://github.com/Bhaskarpattepu/Hybrid_Framework_end_to_end_Java_selenium_Jenkins_docker.git
* cd Hybrid_Framework_end_to_end_Java_selenium_Jenkins_docker

2. Install dependencies

   1. mvn clean install

# Running Tests

Local Execution

    mvn test -DsuiteXmlFile=testng.xml

Other suite files:

* testng.xml → Default regression suite 
* master.xml → End-to-end execution 
* grouping.xml → Group-based execution

Using Dockerized Selenium Grid

    #Start grid
    docker-compose up -d

    #Run tests pointing to grid
    mvn test -DsuiteXmlFile=testng.xml -Dgrid=true

# 🔧 Configuration

* Browsers: Update in config.properties (e.g., Chrome / Firefox)
* Grid URL: Update in grid-docker.xml if using remote execution 
* Test Data: Place files in /testData (Excel/CSV etc.)

# Reports & Logs

* Extent Reports → /reports 
* Screenshots on failure → /screenshots 
* Logs → /logs

# Jenkins Integratio

1. Install Maven & Docker plugins in Jenkins
2. Create a Jenkins job → pull this repo
3. Add build step:
  * mvn clean test -DsuiteXmlFile=testng.xml
4. Archive /reports and /screenshots as build artifacts

# Folder Structure

    Hybrid_Framework_end_to_end_Java_selenium_Jenkins_docker
    │── pom.xml              # Maven dependencies
    │── testng.xml           # TestNG suite config
    │── docker-compose.yaml  # Selenium Grid setup
    │
    ├── /src/main/java    
    │    ├── /pages          # Page Object classes
    ├── /src/test/java       # Test classes & framework code
    │    ├── /testcases          # Test cases
    │           ├── TC001          # Test cases
    │           ├── TC002          # Test cases
    │           ├── TC003          # Test cases
    │    ├── /testBase       
    │           ├── BaseClass       #Driver Setup class
    │
    ├── /testData            # Test data (Excel/CSV/JSON)
    ├── /utilities           # Utilities for DataProviders and Excel Utility and Extent Report Manager
    ├── /reports             # Extent Reports
    ├── /logs                # Execution logs
    ├── /screenshots         # Failure screenshots


# Technologies Used
* Java (core language)
* Selenium WebDriver 
* TestNG (test orchestration)
* Maven (dependency management)
* log4j (logging)
* Extent Reports (reporting)
* Docker + Docker Compose (Grid setup)
* Jenkins (CI/CD)

# Contribution

1. Fork the repo 
2. Create a feature branch (git checkout -b feature/my-feature)
3. Commit changes (git commit -m "Added new feature")
4. Push branch (git push origin feature/my-feature)
5. Open a Pull Request


