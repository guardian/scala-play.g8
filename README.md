# The Guardian Scala Play G8 Template.

This [giter8](https://github.com/foundweekends/giter8) template is designed to be an opinionated template consisting of the boilerplate required when creating a new project or service at the Guardian. It aims to make creating a new project at the Guardian as painless as possible in order to allow developers to spend their time focusing on solving problems.

We believe that we should try and have uniformity as much as we can across our applications and believe that this is the place where we have the conversation as to what should and should not be standardised on. Once we come to an agreement it should be added to the template.

This template provides a project setup for: 
 - A compilable and runnable compile time DI Play project
 - A healtcheck endpoint
 - A basic view template
 - Integration for Play Google Auth
 - Code formatting with Scalariform
 - Deploying via Riff Raff
 - A systemd file to run your application
 - An example test for the healthcheck endpoint
 - A working logback.xml file integrated with the application.
 - A basic cloudformation template for putting your service on AWS


To use, ensure you have SBT 0.13.13 or higher, or G8

Then:

```
sbt new guardian/scala-play.g8
```

You will be asked to fill out some properties required for correctly building your project.

Once done, move to your new project and it should compile and run. Now you can begin customising for your specific projects needs and get started!
