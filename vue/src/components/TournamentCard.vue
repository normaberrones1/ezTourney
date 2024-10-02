<template>
    <div class="tourneyCard">
        <h2>{{ tourney.tourneyName }}</h2>
        <h4>Hosted By: {{ tourney.directorName }}</h4>
        <section class="tourneyNotes">
            <span>Entry Cost: ${{ tourney.entryFee }}</span>
            <span>Start Date: {{ tourney.startDate }}</span>
        </section>
        <p>{{ tourney.tourneyDesc }}</p>
    </div>
</template>

<script>

export default {
    props: ["tourney"]
}
</script>