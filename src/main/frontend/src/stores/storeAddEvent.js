import  {defineStore} from "pinia";

export const storeMyEvents = defineStore ( {
id: "myEvents",
state: () => ( {
    addedEvents: [],
}),
actions:  {
    showAddedEvents(event)  {
        if (this.addedEvents.indexOf(event)=== -1) {
            this.addedEvents.push(event)
        }
    }
}
})