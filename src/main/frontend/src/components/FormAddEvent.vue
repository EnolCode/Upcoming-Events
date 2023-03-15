<script setup>
import { ref } from "vue";
import { Notify, useQuasar } from "quasar";
import axios from "axios";

let titleModel = ref();
let descriptionModel = ref();
let dateModel  = ref();
let capacityModel = ref();

const $q = useQuasar();

const onSubmit = async () => {
  axios({
    method: "POST",
    url: "http://localhost:8080/api/events",
    data: {
      title: titleModel.value,
      date_hour: dateModel.value,
      max_participants: parseInt(capacityModel.value),
      description: descriptionModel.value,
      actual_participants: 200
    },
  })
    .then((res) =>
      $q.notify({
        type: "positive",
        message: "Event added successfully!",
        icon: "fa-solid fa-circle-check",
      })
    )
    .catch((err) => console.log(err));
  onReset();
};

const onReset = () => {
  titleModel.value = "",
  descriptionModel.value = "",
  dateModel.value = "",
  capacityModel.value = ""
};
</script>

<template>
  <q-page class="row justify-center text-center bg-blue">
    <q-img
      src="../assets/images/concierto-form.jpg"
      spinner-color="white"
      class="col-0 col-xl-7 col-lg-7 col-md-7 "
      height="100vh"
    />

    <div class="container-form column col-5 justify-between">

<div class="column container-icons space-between col-1 ">
  <q-breadcrumbs-el icon="fa-solid fa-house" class="q-mt-xl pointer gt-sm"  color="white" to="/" style="font-size:2em;" />

      <q-img
        src="../assets/images/logo.png"
        class="self-end q-mt-lg col-sm-12 logo"

      />
  </div>

      <q-form @submit="onSubmit" @reset="onReset" class="form q-gutter-xs  self-end ">
        <span class="text-white text-h3 text-weight-bold">Register New Event</span>
        <q-input
          v-model="titleModel"
          label="Enter event name"
          label-color="white"
          outlined
          rounded
          text-color="white"
          color="white"
          type="text"
          :input-style="{ color: 'white', fontSize: '1.7em'}"
          lazy-rules="ondemand"
          bg-color="red"
          class="q-mt-lg"
          :rules="[
            (val) => (val && val.length > 3 ) || 'This field is required',
             (val) => (val && val.length <= 40 ) || 'This field cannot have more than 40 characters'
          ]"

        />
        <q-input
          v-model="descriptionModel"
          label="Description"
          label-color="white"
          outlined
          rounded
          text-color="white"
          color="white"
          type="text"
          :input-style="{ color: 'white', fontSize: '1.7em'}"
          lazy-rules="ondemand"
          bg-color="red"
          class="q-mt-lg"
          :rules="[
            (val) => (val && val.length > 3) || 'This field is required',
          ]"
        />
        <!-- <q-input
          v-model="dateModel"
          label="Date/Time"
          label-color="white"
          outlined
          rounded
          text-color="white"
          color="white"
          type="text"
          :input-style="{ color: 'white', fontSize: '1.7em'}"
          lazy-rules="ondemand"
          bg-color="red"
          class="q-mt-lg"
          :rules="[
            (val) => (val && val.length > 3) || 'This field is required',
          ]"
        /> -->

        <q-input
          v-model="dateModel"
          label=""
          label-color="white"
          outlined
          rounded
          text-color="white"
          color="white"
          type="date"
          :input-style="{ color: 'white', fontSize: '1.3em', marginBottom: '1.1em'}"
          lazy-rules="ondemand"
          bg-color="red"
          class="q-mt-lg"
          :rules="[
            (val) => (val && val.length > 3) || 'This field is required',
          ]"
        />

        <q-input
          v-model="capacityModel"
          label="Total Capacity"
          label-color="white"
          outlined
          rounded
          text-color="white"
          color="white"
          type="text"
          :input-style="{ color: 'white', fontSize: '1.7em'}"
          lazy-rules="ondemand"
          bg-color="red"
          class="q-mt-lg"
          :rules="[
            (val) => (val && val.length > 1) || 'This field is required',
          ]"
        />

        <q-btn
            label="New Here?"
            type="submit"
            color="white"
            class="q-py-md text-center button-new"
            flat
          />

        <div class="row justify-between">
          <q-btn
            unelevated
            outlined
            rounded
            label="Submit"
            type="submit"
            color="blue-1"
            class="col-5 q-py-md"
          />

          <q-btn
            outlined
            rounded
            label="Reset"
            type="reset"
            color="white"
            flat
            class="col-5 q-py-md btn-reset"
          />

        </div>
      </q-form>
    </div>
  </q-page>
</template>

<style lang="scss" scoped>
.btn-reset {
  border: 1px solid $brown;
  color: white;
}

.container-form{
  height: 90vh;
}

.form{
  width: 85%;
  margin: 0 auto;
}

.container-icons{
  margin: 0 auto;
  width: 84%;
}

.q-breadcrumbs__el {
    color: white;
}
.pointer{
  cursor: pointer;
  &:hover{
  opacity: .7;
  }
}
.logo{
  width: 16em;
  height: 6em;
}
</style>
