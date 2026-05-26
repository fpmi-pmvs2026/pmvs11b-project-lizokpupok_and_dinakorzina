# Functional Requirements

| ID | Requirement |
|----|-------------|
| FR-01 | User can create a trip with title, destination, dates, budget |
| FR-02 | User can view the list of all trips |
| FR-03 | User can edit an existing trip |
| FR-04 | User can delete a trip (places deleted by cascade) |
| FR-05 | User can view trip details with places |
| FR-06 | User can add a place to a trip |
| FR-07 | User can toggle a place as visited |
| FR-08 | User can set estimated cost per place |
| FR-09 | App calculates total spent and budget remaining |
| FR-10 | App fetches country info via REST Countries API |

## Use Case Diagram (PlantUML)

```plantuml
@startuml
actor User

rectangle "Travel Planner" {
  usecase "Create trip" as UC1
  usecase "View trips" as UC2
  usecase "Edit trip" as UC3
  usecase "Delete trip" as UC4
  usecase "View details" as UC5
  usecase "Add place" as UC6
  usecase "Toggle visited" as UC7
  usecase "Check budget" as UC8
  usecase "Get country info" as UC9
}

User --> UC1
User --> UC2
User --> UC3
User --> UC4
User --> UC5
User --> UC6
User --> UC7
User --> UC8
User --> UC9
@enduml
```
