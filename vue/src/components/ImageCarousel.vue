<template>
  <div class="image-carousel">
    <h1>Image Carousel</h1>
    <p> This is an image carousel</p>
    <div>
      <img v-for="image in carousel" :src="require(`../assets/${image.src}`)" :alt="image.alt" :key="image.id">
    </div>
    



  </div>
</template>

<script>
export default {

}
</script>

<style>

</style>