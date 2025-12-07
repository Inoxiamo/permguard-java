# The official Java SDK for Permguard

[![GitHub License](https://img.shields.io/github/license/permguard/sdk-java)](https://github.com/permguard/sdk-java?tab=Apache-2.0-1-ov-file#readme)
[![X (formerly Twitter) Follow](https://img.shields.io/twitter/follow/permguard)](https://x.com/intent/follow?original_referer=https%3A%2F%2Fdeveloper.x.com%2F&ref_src=twsrc%5Etfw%7Ctwcamp%5Ebuttonembed%7Ctwterm%5Efollow%7Ctwgr%5ETwitterDev&screen_name=Permguard)

[![Documentation](https://img.shields.io/website?label=Docs&url=https%3A%2F%2Fwww.permguard.com%2F)](https://www.permguard.com/)
[![Build, test and publish the artifacts](https://github.com/permguard/sdk-java/actions/workflows/sdk-java-ci.yml/badge.svg)](https://github.com/permguard/sdk-java/actions/workflows/sdk-java-ci.yml)

[![Watch the video on YouTube](https://raw.githubusercontent.com/permguard/permguard-assets/refs/heads/main/video/permguard-thumbnail-preview.png)](https://youtu.be/cH_boKCpLQ8?si=i1fWFHT5kxQQJoYN)

[Watch the video on YouTube](https://youtu.be/cH_boKCpLQ8?si=i1fWFHT5kxQQJoYN)



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
        <artifactId>permguard</artifactId>
        <version>0.0.2</version>
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
    AZConfig config = new AZConfig("localhost", 9094, true);
    AZClient client = new AZClient(config);

    long zoneId = 611159836099L;
            String policyStoreId = "f96586c317c74aaaae4ff2ba2fef0459";
            String requestId = "abc1";

            Principal principal = new PrincipalBuilder("amy.smith@acmecorp.com")
                    .withType("user")
                    .withSource("keycloak")
                    .build();

            Entities entities = new Entities("cedar", List.of(
                    Map.of(
                            "uid", Map.of("type", "PharmaAuthZFlow::Platform::BranchInfo", "id", "subscription"),
                            "attrs", Map.of("active", true),
                            "parents", List.of()
                    )
            ));

            // ✅ Build the atomic AZRequest using the exact JSON parameters
            AZRequest request = new AZAtomicRequestBuilder(
                    zoneId,
                    policyStoreId,
                    "platform-creator",  // Subject id from JSON
                    "PharmaAuthZFlow::Platform::Subscription",  // Resource type from JSON
                    "PharmaAuthZFlow::Platform::Action::create"  // Action name from JSON
            )
                    .withRequestId(requestId)
                    .withPrincipal(principal)
                    .withEntitiesItems("cedar", entities)
                    .withSubjectSource("keycloak")
                    .withSubjectProperty("isSuperUser", true)
                    .withResourceId("e3a786fd07e24bfa95ba4341d3695ae8")
                    .withResourceProperty("isEnabled", true)
                    .withActionProperty("isEnabled", true)
                    .withContextProperty("time", "2025-01-23T16:17:46+00:00")
                    .withContextProperty("isSubscriptionActive", true)
                    .build();

            AZResponse response = client.check(request);
            if (response == null) {
                System.out.println("❌ Authorization request failed.");
                return;
            }
    
            if (response.isDecision()) {
                System.out.println("✅ Authorization Permitted");
            } else {
                System.out.println("❌ Authorization request failed.");
            }
}
```

---

## Configuration

The SDK uses the `AZConfig` class to hold connection parameters for your Permguard PDP service. For example:

```java
    AZConfig config = new AZConfig("localhost", 9094, true);
    AZClient client = new AZClient(config);
```

- **host**: The hostname or IP address of your PDP service.
- **port**: The port number.
- **usePlaintext**: Use plaintext if TLS is not required; otherwise, configure TLS as needed.

**Permguard** rethinks Authorization Beyond the Token. From Static Claims to Runtime Trust: Secure, Auditable Decisions for Humans and Workloads. Boost SIEM & SOC with Decision Insights.
This repository implements the Permguard Java SDK (Authorization Check).

---

## Version Compatibility

Our SDK follows a versioning scheme aligned with the AuthZServer versions to ensure seamless integration. The versioning format is as follows:

**SDK Versioning Format:** `x.y.z`

- **x.y**: Indicates the compatible AuthZServer version.
- **z**: Represents the SDK's patch or minor updates specific to that server version.

**Compatibility Examples:**

- `SDK Version 1.3.0` is compatible with `AuthZServer 1.3`.
- `SDK Version 1.3.1` includes minor improvements or bug fixes for `AuthZServer 1.3`.

**Incompatibility Example:**

- `SDK Version 1.3.0` **may not be guaranteed** to be compatible with `AuthZServer 1.4` due to potential changes introduced in server version `1.4`.

**Important:** Ensure that the major and minor versions (`x.y`) of the SDK match those of your AuthZServer to maintain compatibility.

---

Created by [Nitro Agility](https://www.nitroagility.com/).
