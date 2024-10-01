<template>
  <div class="home">
    <h1>Home</h1>
    <!-- remember to make this page only visible if authenticated -->
    <h2>You must be authenticated to see this</h2>
    <p>And let me tell you I plan to enforce that</p>
    <div>
      <p>displayed here will be current tourneys with links to their info</p>
    </div>

  
    
    
  </div>
</template>

<script>


export default {
  name: 'Home',
  data() {
    return {
      carousel: [
        {
          id: 1,
          src: 'carousel1.jpg',
          alt: 'carousel image 1'
        },
        {
          id: 2,
          src: 'carousel2.jpg',
          alt: 'carousel image 2'
        },
        {
          id: 3,
          src: 'carousel3.jpg',
          alt: 'carousel image 3'
        }
      ]
    }
  }
  
};
</script>
