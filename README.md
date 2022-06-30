## Health & Fitness Application

This application is for the people who are trying to become fit and healthy, and are determined to make positive changes in their lives by doing so. Whether it is to lose or gain weight, 
this app can help. It uses your weight, height,
age, gender, and lifestyle to calculate your 
*total daily energy expenditure* which can help you make an informed decision about your health and nutrition. Shows you a variety of healthy foods and exercises, too!

A non-exhaustive list of uses of this application:
- To view exercises for different groups of muscles.
- To calculate your **TDEE**.
- To view diets according to preference.
- To add the diet you like to your diet plan.

The main motivation behind 
the creation of this project was to guide people
towards the fundamentals of bodybuilding which a lot 
of people tend to ignore -- *nutrition and essential 
exercises*. 
Nutrition is a very important part of bodybuilding, 
and for me personally, the most important one.
When people complain about not putting on enough muscle
for the long hours they workout in the gym, 
it is most probably because their food 
intake is lacking. It is extremely important to 
meet your protein and macro goals to ensure a healthy growth of your muscles.

## User Stories: 
- As a user, I want to be able to view my TDEE.
- As a user, I want to be able to view diet plans.
- As a user, I want to be able to add diet plans to my diet.
- As a user, I want to be able to view different exercises for different muscle groups.
- As a user, I want to be able to save all details entered when I first use the app.
- As a user, I want to be able to read the details that I entered.

## Phase 4: Task 2
Fri Apr 01 10:41:42 PDT 2022 \
Added person to list of people \
Fri Apr 01 10:41:48 PDT 2022 \
Added diet to diet plan

- The above lines are printed in the console when the user exits the app after adding information about them and adding a diet plan.

## Phase 4: Task 3

### Reflection:
- UI class uses JsonReader and JsonWriter.
- Writable is an interface being used by Person and People.
- People class consists of list of Person class.
- DietPlans consists of list of ClassDiet.

### Refactoring:
- If I had more time, I would make the GUI more aesthetic and coherent.
- Using Single Responsibility Principle would make the code neater and easier to edit.
- Reducing the number of classes by removing duplicate code and aggregating classes.