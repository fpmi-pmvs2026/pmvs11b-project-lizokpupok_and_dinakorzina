# Use Cases

## UC-01 Create Trip

**Actor:** User  
**Precondition:** App is open on Trips screen  
**Steps:**
1. Tap "+" button
2. Fill in title, destination, start date, end date, budget
3. Tap Save

**Postcondition:** Trip appears in the list; stored in Room DB

---

## UC-02 View Trip List

**Actor:** User  
**Steps:** Open app — trips list is displayed (reactive Flow from Room)

---

## UC-03 Edit Trip

**Actor:** User  
**Precondition:** At least one trip exists  
**Steps:**
1. Tap trip → Details screen
2. Tap Edit
3. Modify fields → Save

**Postcondition:** Trip updated in DB

---

## UC-04 Delete Trip

**Actor:** User  
**Steps:** Swipe / long-press trip → Delete  
**Postcondition:** Trip and all its places deleted (CASCADE)

---

## UC-05 Add Place

**Actor:** User  
**Precondition:** Trip details screen is open  
**Steps:**
1. Tap "Add Place"
2. Fill title, description, category, estimated cost
3. Save

**Postcondition:** Place linked to trip via `tripId` FK

---

## UC-06 Toggle Visited

**Actor:** User  
**Steps:** Tap checkbox next to place  
**Postcondition:** `isVisited` toggled in DB; budget recalculated reactively

---

## UC-07 Check Budget

**Actor:** User  
**Steps:** Open Budget screen for a trip  
**Result:** See total budget, total spent (sum of visited place costs), remaining

---

## UC-08 Get Country Info

**Actor:** User  
**Steps:** On trip details — app calls REST Countries API with destination name  
**Result:** Capital, region, currency, population, flag displayed
