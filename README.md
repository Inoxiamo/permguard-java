# Permguard Java SDK

[![GitHub License](https://img.shields.io/github/license/permguard/permguard-java)](https://github.com/permguard/permguard-java?tab=Apache-2.0-1-ov-file#readme)
[![X (formerly Twitter) Follow](https://img.shields.io/twitter/follow/permguard)](https://x.com/intent/follow?original_referer=https%3A%2F%2Fdeveloper.x.com%2F&ref_src=twsrc%5Etfw%7Ctwcamp%5Ebuttonembed%7Ctwterm%5Efollow%7Ctwgr%5ETwitterDev&screen_name=Permguard)

[![Documentation](https://img.shields.io/website?label=Docs&url=https%3A%2F%2Fwww.permguard.com%2F)](https://www.permguard.com/)
[![Build, test and publish the artifacts](https://github.com/permguard/permguard-java/actions/workflows/permguard-java-ci.yml/badge.svg)](https://github.com/permguard/permguard-java/actions/workflows/permguard-java-ci.yml)

<p align="left">
  <img src="https://raw.githubusercontent.com/permguard/permguard-assets/main/pink-txt//1line.svg" class="center" width="400px" height="auto"/>
</p>


The Permguard Java SDK provides a simple and flexible client to perform authorization checks against a Permguard Policy Decision Point (PDP) service using gRPC. This README explains how to install the SDK, configure the client, and integrate it into your Java application.

---

## Prerequisites

- **Java 17**
- **Maven**

---

## Installation

Add the following dependency and build configuration to your project's `pom.xml` file:

```xml
<dependencies>
    <dependency>
        <groupId>com.permguard.pep</groupId>
        <artifactId>permguard-java</artifactId>
        <version>0.0.4-SNAPSHOT</version>
    </dependency>
</dependencies>

<build>
    <plugins>
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-compiler-plugin</artifactId>
            <version>3.11.0</version>
            <configuration>
                <source>17</source>
                <target>17</target>
            </configuration>
        </plugin>
    </plugins>
</build>
```

---

## Usage Example

Below is a sample Java `main` method demonstrating how to create a Permguard client, build an authorization request using a builder pattern, and process the authorization response:

```java
public static void main(String[] args) {
    // Create and configure the Permguard client.
    AZClient client = new AZClient(
            new AZConfig.Builder("localhost", 9094).build()
    );

    try {
        // Build request details using the builder pattern for clarity.
        Item itemDetails = new Item.Builder("MagicFarmacia::Platform::BranchInfo", "subscription",
                Map.of("active", true), List.of()).build();
        Entity entityDetail1 = new Entity.Builder("cedar", List.of(itemDetails)).build();
        PolicyStore policyStoreDetail = new PolicyStore.Builder("323437219436", "ledger",
                "9b8030f0edb949c0b743bd13b8396c15").build();
        Principal principalDetail = new Principal.Builder("user", "amy.smith@acmecorp.com", "keycloak")
                .identityToken("eyJhbGciOiJI...").accessToken("eyJhbGciOiJI...").build();
        Subject subjectDetail = new Subject.Builder("user", "amy.smith@acmecorp.com", "keycloak",
                Map.of("isSuperUser", true)).build();
        Resource resourceDetail = new Resource.Builder("MagicFarmacia::Platform::Subscription",
                "e3a786fd07e24bfa95ba4341d3695ae8", Map.of("isEnabled", true)).build();
        Action actionDetail = new Action.Builder("MagicFarmacia::Platform::Action::create",
                Map.of("isEnabled", true)).build();
        Map<String, Object> context = Map.of(
                "isSubscriptionActive", true,
                "time", "2025-01-23T16:17:46+00:00"
        );

        // Perform the authorization check.
        AuthResponsePayload response = client.check(policyStoreDetail, actionDetail,
                principalDetail, resourceDetail, entityDetail1, subjectDetail, context);

        // Handle the response.
        if (response.isDecision()) {
            System.out.println("✅ Request Authorized");
        } else {
            System.out.println("❌ Request Not Authorized");
        }
    } catch (Exception e) {
        System.err.println("Error managing request: " + e.getMessage());
        e.printStackTrace();
    } finally {
        // Close the client.
        client.shutdown();
    }
}
```

---

## Configuration

The SDK uses the `AZConfig` class to hold connection parameters for your Permguard PDP service. For example:

```java
AZConfig config = new AZConfig.Builder("localhost", 9094)
    .usePlaintext(true) // set to true to use plaintext communication
    .build();
```

- **host**: The hostname or IP address of your PDP service.
- **port**: The port number.
- **usePlaintext**: Use plaintext if TLS is not required; otherwise, configure TLS as needed.




**Permguard** is an Open Source ZTAuth* Provider for cloud-native, edge, and multi-tenant apps, decoupled from application code and leveraging `Policy-as-Code` for centralized, scalable permission management.

This repository implements the Permguard Java SDK (Authorization Check).

Created by [Nitro Agility](https://www.nitroagility.com/).
