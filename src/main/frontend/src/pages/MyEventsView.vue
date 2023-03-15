<script setup>
import SliderComponent from 'src/components/SliderComponent.vue';


</script>

<template>
<HeadPage/>
<SliderComponent/>
<h1>My Events</h1>

<div class="q-pa-md">
    <q-table
      grid
      card-class="bg-primary text-white"
      title="Treats"
      :rows="rows"
      :columns="columns"
      row-key="name"
      :filter="filter"
      hide-header
    >
      <template v-slot:top-right>
        <q-input borderless dense debounce="300" v-model="filter" placeholder="Search">
          <template v-slot:append>
            <q-icon name="search" />
          </template>
        </q-input>
      </template>
    </q-table>
  </div>
</template>