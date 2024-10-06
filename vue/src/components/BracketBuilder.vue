<template>
    <label for="numTeams" class="numTeamsTitle">Number of Teams:</label>
    <input type="number" id="numTeams" v-model="numTeams" min="2" max="64" required />

    <!-- this is the bar of all possible rounds (max 7) -->
    <div class="roundTitleBar">
        <div v-if="numTeams > 32">
            <div class="roundTitle">Round</div>

            <div class="bracket">
                <label for ="teamName">Team Name:</label>
                <input type="text" id="teamName1" class="bracketText" v-model="teamName" required />
            </div>

            <div class="bracket">
                <label for ="teamName">Team Name:</label>
                <input type="text" id="teamName2" class="bracketText" v-model="teamName" required />
            </div>

            <div class="bracket">
                <label for ="teamName">Team Name:</label>
                <input type="text" id="teamName3" class="bracketText" v-model="teamName" required />
            </div>

            <div class="bracket">
                <label for ="teamName">Team Name:</label>
                <input type="text" id="teamName4" class="bracketText" v-model="teamName" required />
            </div>

            <div class="bracket">
                <label for ="teamName">Team Name:</label>
                <input type="text" id="teamName5" class="bracketText" v-model="teamName" required />
            </div>

            <div class="bracket">
                <label for ="teamName">Team Name:</label>
                <input type="text" id="teamName6" class="bracketText" v-model="teamName" required />
            </div>

            <div class="bracket">
                <label for ="teamName">Team Name:</label>
                <input type="text" id="teamName7" class="bracketText" v-model="teamName" required />
            </div>

            <div class="bracket">
                <label for ="teamName">Team Name:</label>
                <input type="text" id="teamName8" class="bracketText" v-model="teamName" required />
            </div>

            <div class="bracket">
                <label for ="teamName">Team Name:</label>
                <input type="text" id="teamName9" class="bracketText" v-model="teamName" required />
            </div>

            <div class="bracket">
                <label for ="teamName">Team Name:</label>
                <input type="text" id="teamName10" class="bracketText" v-model="teamName" required />
            </div>

            <div class="bracket">
                <label for ="teamName">Team Name:</label>
                <input type="text" id="teamName11" class="bracketText" v-model="teamName" required />
            </div>

            <div class="bracket">
                <label for ="teamName">Team Name:</label>
                <input type="text" id="teamName12" class="bracketText" v-model="teamName" required />
            </div>

            <div class="bracket">
                <label for ="teamName">Team Name:</label>
                <input type="text" id="teamName13" class="bracketText" v-model="teamName" required />
            </div>

            <div class="bracket">
                <label for ="teamName">Team Name:</label>
                <input type="text" id="teamName14" class="bracketText" v-model="teamName" required />
            </div>

            <div class="bracket">
                <label for ="teamName">Team Name:</label>
                <input type="text" id="teamName15" class="bracketText" v-model="teamName" required />
            </div>

            <div class="bracket">
                <label for ="teamName">Team Name:</label>
                <input type="text" id="teamName16" class="bracketText" v-model="teamName" required />
            </div>

            <div class="bracket">
                <label for ="teamName">Team Name:</label>
                <input type="text" id="teamName17" class="bracketText" v-model="teamName" required />
            </div>

            <div class="bracket">
                <label for ="teamName">Team Name:</label>
                <input type="text" id="teamName18" class="bracketText" v-model="teamName" required />
            </div>

            <div class="bracket">
                <label for ="teamName">Team Name:</label>
                <input type="text" id="teamName19" class="bracketText" v-model="teamName" required />
            </div>

            <div class="bracket">
                <label for ="teamName">Team Name:</label>
                <input type="text" id="teamName20" class="bracketText" v-model="teamName" required />
            </div>

            <div class="bracket">
                <label for ="teamName">Team Name:</label>
                <input type="text" id="teamName21" class="bracketText" v-model="teamName" required />
            </div>

            <div class="bracket">
                <label for ="teamName">Team Name:</label>
                <input type="text" id="teamName22" class="bracketText" v-model="teamName" required />
            </div>

            <div class="bracket">
                <label for ="teamName">Team Name:</label>
                <input type="text" id="teamName23" class="bracketText" v-model="teamName" required />
            </div>

            <div class="bracket">
                <label for ="teamName">Team Name:</label>
                <input type="text" id="teamName24" class="bracketText" v-model="teamName" required />
            </div>

            <div class="bracket">
                <label for ="teamName">Team Name:</label>
                <input type="text" id="teamName25" class="bracketText" v-model="teamName" required />
            </div>

            <div class="bracket">
                <label for ="teamName">Team Name:</label>
                <input type="text" id="teamName26" class="bracketText" v-model="teamName" required />
            </div>

            <div class="bracket">
                <label for ="teamName">Team Name:</label>
                <input type="text" id="teamName27" class="bracketText" v-model="teamName" required />
            </div>

            <div class="bracket">
                <label for ="teamName">Team Name:</label>
                <input type="text" id="teamName28" class="bracketText" v-model="teamName" required />
            </div>

            <div class="bracket">
                <label for ="teamName">Team Name:</label>
                <input type="text" id="teamName29" class="bracketText" v-model="teamName" required />
            </div>

            <div class="bracket">
                <label for ="teamName">Team Name:</label>
                <input type="text" id="teamName30" class="bracketText" v-model="teamName" required />
            </div>

            <div class="bracket">
                <label for ="teamName">Team Name:</label>
                <input type="text" id="teamName31" class="bracketText" v-model="teamName" required />
            </div>

            <div class="bracket">
                <label for ="teamName">Team Name:</label>
                <input type="text" id="teamName32" class="bracketText" v-model="teamName" required />
            </div>

            <div class="bracket">
                <label for ="teamName">Team Name:</label>
                <input type="text" id="teamName33" class="bracketText" v-model="teamName" required />
            </div>

            <div class="bracket">
                <label for ="teamName">Team Name:</label>
                <input type="text" id="teamName34" class="bracketText" v-model="teamName" required />
            </div>

            <div class="bracket">
                <label for ="teamName">Team Name:</label>
                <input type="text" id="teamName35" class="bracketText" v-model="teamName" required />
            </div>

            <div class="bracket">
                <label for ="teamName">Team Name:</label>
                <input type="text" id="teamName36" class="bracketText" v-model="teamName" required />
            </div>

            <div class="bracket">
                <label for ="teamName">Team Name:</label>
                <input type="text" id="teamName37" class="bracketText" v-model="teamName" required />
            </div>

            <div class="bracket">
                <label for ="teamName">Team Name:</label>
                <input type="text" id="teamName38" class="bracketText" v-model="teamName" required />
            </div>

            <div class="bracket">
                <label for ="teamName">Team Name:</label>
                <input type="text" id="teamName39" class="bracketText" v-model="teamName" required />
            </div>

            <div class="bracket">
                <label for ="teamName">Team Name:</label>
                <input type="text" id="teamName40" class="bracketText" v-model="teamName" required />
            </div>

            <div class="bracket">
                <label for ="teamName">Team Name:</label>
                <input type="text" id="teamName41" class="bracketText" v-model="teamName" required />
            </div>

            <div class="bracket">
                <label for ="teamName">Team Name:</label>
                <input type="text" id="teamName42" class="bracketText" v-model="teamName" required />
            </div>

            <div class="bracket">
                <label for ="teamName">Team Name:</label>
                <input type="text" id="teamName43" class="bracketText" v-model="teamName" required />
            </div>

            <div class="bracket">
                <label for ="teamName">Team Name:</label>
                <input type="text" id="teamName44" class="bracketText" v-model="teamName" required />
            </div>

            <div class="bracket">
                <label for ="teamName">Team Name:</label>
                <input type="text" id="teamName45" class="bracketText" v-model="teamName" required />
            </div>

            <div class="bracket">
                <label for ="teamName">Team Name:</label>
                <input type="text" id="teamName46" class="bracketText" v-model="teamName" required />
            </div>

            <div class="bracket">
                <label for ="teamName">Team Name:</label>
                <input type="text" id="teamName47" class="bracketText" v-model="teamName" required />
            </div>

            <div class="bracket">
                <label for ="teamName">Team Name:</label>
                <input type="text" id="teamName48" class="bracketText" v-model="teamName" required />
            </div>

            <div class="bracket">
                <label for ="teamName">Team Name:</label>
                <input type="text" id="teamName49" class="bracketText" v-model="teamName" required />
            </div>

            <div class="bracket">
                <label for ="teamName">Team Name:</label>
                <input type="text" id="teamName50" class="bracketText" v-model="teamName" required />
            </div>

            <div class="bracket">
                <label for ="teamName">Team Name:</label>
                <input type="text" id="teamName51" class="bracketText" v-model="teamName" required />
            </div>

            <div class="bracket">
                <label for ="teamName">Team Name:</label>
                <input type="text" id="teamName52" class="bracketText" v-model="teamName" required />
            </div>

            <div class="bracket">
                <label for ="teamName">Team Name:</label>
                <input type="text" id="teamName53" class="bracketText" v-model="teamName" required />
            </div>

            <div class="bracket">
                <label for ="teamName">Team Name:</label>
                <input type="text" id="teamName54" class="bracketText" v-model="teamName" required />
            </div>

            <div class="bracket">
                <label for ="teamName">Team Name:</label>
                <input type="text" id="teamName55" class="bracketText" v-model="teamName" required />
            </div>

            <div class="bracket">
                <label for ="teamName">Team Name:</label>
                <input type="text" id="teamName56" class="bracketText" v-model="teamName" required />
            </div>

            <div class="bracket">
                <label for ="teamName">Team Name:</label>
                <input type="text" id="teamName57" class="bracketText" v-model="teamName" required />
            </div>

            <div class="bracket">
                <label for ="teamName">Team Name:</label>
                <input type="text" id="teamName58" class="bracketText" v-model="teamName" required />
            </div>

            <div class="bracket">
                <label for ="teamName">Team Name:</label>
                <input type="text" id="teamName59" class="bracketText" v-model="teamName" required />
            </div>

            <div class="bracket">
                <label for ="teamName">Team Name:</label>
                <input type="text" id="teamName60" class="bracketText" v-model="teamName" required />
            </div>

            <div class="bracket">
                <label for ="teamName">Team Name:</label>
                <input type="text" id="teamName61" class="bracketText" v-model="teamName" required />
            </div>

            <div class="bracket">
                <label for ="teamName">Team Name:</label>
                <input type="text" id="teamName62" class="bracketText" v-model="teamName" required />
            </div>

            <div class="bracket">
                <label for ="teamName">Team Name:</label>
                <input type="text" id="teamName63" class="bracketText" v-model="teamName" required />
            </div>

            <div class="bracket">
                <label for ="teamName">Team Name:</label>
                <input type="text" id="teamName64" class="bracketText" v-model="teamName" required />
            </div>

        </div>
        <div v-if="numTeams > 16">
            <div class="roundTitle">Round</div>

            <div class="bracket">
                <label for ="teamName">Team Name:</label>
                <input type="text" id="teamName1" class="bracketText" v-model="teamName" required />
            </div>

            <div class="bracket">
                <label for ="teamName">Team Name:</label>
                <input type="text" id="teamName2" class="bracketText" v-model="teamName" required />
            </div>

            <div class="bracket">
                <label for ="teamName">Team Name:</label>
                <input type="text" id="teamName3" class="bracketText" v-model="teamName" required />
            </div>

            <div class="bracket">
                <label for ="teamName">Team Name:</label>
                <input type="text" id="teamName4" class="bracketText" v-model="teamName" required />
            </div>

            <div class="bracket">
                <label for ="teamName">Team Name:</label>
                <input type="text" id="teamName5" class="bracketText" v-model="teamName" required />
            </div>

            <div class="bracket">
                <label for ="teamName">Team Name:</label>
                <input type="text" id="teamName6" class="bracketText" v-model="teamName" required />
            </div>

            <div class="bracket">
                <label for ="teamName">Team Name:</label>
                <input type="text" id="teamName7" class="bracketText" v-model="teamName" required />
            </div>

            <div class="bracket">
                <label for ="teamName">Team Name:</label>
                <input type="text" id="teamName8" class="bracketText" v-model="teamName" required />
            </div>

            <div class="bracket">
                <label for ="teamName">Team Name:</label>
                <input type="text" id="teamName9" class="bracketText" v-model="teamName" required />
            </div>

            <div class="bracket">
                <label for ="teamName">Team Name:</label>
                <input type="text" id="teamName10" class="bracketText" v-model="teamName" required />
            </div>

            <div class="bracket">
                <label for ="teamName">Team Name:</label>
                <input type="text" id="teamName11" class="bracketText" v-model="teamName" required />
            </div>

            <div class="bracket">
                <label for ="teamName">Team Name:</label>
                <input type="text" id="teamName12" class="bracketText" v-model="teamName" required />
            </div>

            <div class="bracket">
                <label for ="teamName">Team Name:</label>
                <input type="text" id="teamName13" class="bracketText" v-model="teamName" required />
            </div>

            <div class="bracket">
                <label for ="teamName">Team Name:</label>
                <input type="text" id="teamName14" class="bracketText" v-model="teamName" required />
            </div>

            <div class="bracket">
                <label for ="teamName">Team Name:</label>
                <input type="text" id="teamName15" class="bracketText" v-model="teamName" required />
            </div>

            <div class="bracket">
                <label for ="teamName">Team Name:</label>
                <input type="text" id="teamName16" class="bracketText" v-model="teamName" required />
            </div>

            <div class="bracket">
                <label for ="teamName">Team Name:</label>
                <input type="text" id="teamName17" class="bracketText" v-model="teamName" required />
            </div>

            <div class="bracket">
                <label for ="teamName">Team Name:</label>
                <input type="text" id="teamName18" class="bracketText" v-model="teamName" required />
            </div>

            <div class="bracket">
                <label for ="teamName">Team Name:</label>
                <input type="text" id="teamName19" class="bracketText" v-model="teamName" required />
            </div>

            <div class="bracket">
                <label for ="teamName">Team Name:</label>
                <input type="text" id="teamName20" class="bracketText" v-model="teamName" required />
            </div>

            <div class="bracket">
                <label for ="teamName">Team Name:</label>
                <input type="text" id="teamName21" class="bracketText" v-model="teamName" required />
            </div>

            <div class="bracket">
                <label for ="teamName">Team Name:</label>
                <input type="text" id="teamName22" class="bracketText" v-model="teamName" required />
            </div>

            <div class="bracket">
                <label for ="teamName">Team Name:</label>
                <input type="text" id="teamName23" class="bracketText" v-model="teamName" required />
            </div>

            <div class="bracket">
                <label for ="teamName">Team Name:</label>
                <input type="text" id="teamName24" class="bracketText" v-model="teamName" required />
            </div>

            <div class="bracket">
                <label for ="teamName">Team Name:</label>
                <input type="text" id="teamName25" class="bracketText" v-model="teamName" required />
            </div>

            <div class="bracket">
                <label for ="teamName">Team Name:</label>
                <input type="text" id="teamName26" class="bracketText" v-model="teamName" required />
            </div>

            <div class="bracket">
                <label for ="teamName">Team Name:</label>
                <input type="text" id="teamName27" class="bracketText" v-model="teamName" required />
            </div>

            <div class="bracket">
                <label for ="teamName">Team Name:</label>
                <input type="text" id="teamName28" class="bracketText" v-model="teamName" required />
            </div>

            <div class="bracket">
                <label for ="teamName">Team Name:</label>
                <input type="text" id="teamName29" class="bracketText" v-model="teamName" required />
            </div>

            <div class="bracket">
                <label for ="teamName">Team Name:</label>
                <input type="text" id="teamName30" class="bracketText" v-model="teamName" required />
            </div>

            <div class="bracket">
                <label for ="teamName">Team Name:</label>
                <input type="text" id="teamName31" class="bracketText" v-model="teamName" required />
            </div>

            <div class="bracket">
                <label for ="teamName">Team Name:</label>
                <input type="text" id="teamName32" class="bracketText" v-model="teamName" required />
            </div>

        </div>
        <div v-if="numTeams > 8">
            <div class="roundTitle">Round</div>

            <div class="bracket">
                <label for ="teamName">Team Name:</label>
                <input type="text" id="teamName1" class="bracketText" v-model="teamName" required />
            </div>

            <div class="bracket">
                <label for ="teamName">Team Name:</label>
                <input type="text" id="teamName2" class="bracketText" v-model="teamName" required />
            </div>

            <div class="bracket">
                <label for ="teamName">Team Name:</label>
                <input type="text" id="teamName3" class="bracketText" v-model="teamName" required />
            </div>

            <div class="bracket">
                <label for ="teamName">Team Name:</label>
                <input type="text" id="teamName4" class="bracketText" v-model="teamName" required />
            </div>

            <div class="bracket">
                <label for ="teamName">Team Name:</label>
                <input type="text" id="teamName5" class="bracketText" v-model="teamName" required />
            </div>

            <div class="bracket">
                <label for ="teamName">Team Name:</label>
                <input type="text" id="teamName6" class="bracketText" v-model="teamName" required />
            </div>

            <div class="bracket">
                <label for ="teamName">Team Name:</label>
                <input type="text" id="teamName7" class="bracketText" v-model="teamName" required />
            </div>

            <div class="bracket">
                <label for ="teamName">Team Name:</label>
                <input type="text" id="teamName8" class="bracketText" v-model="teamName" required />
            </div>

            <div class="bracket">
                <label for ="teamName">Team Name:</label>
                <input type="text" id="teamName9" class="bracketText" v-model="teamName" required />
            </div>

            <div class="bracket">
                <label for ="teamName">Team Name:</label>
                <input type="text" id="teamName10" class="bracketText" v-model="teamName" required />
            </div>

            <div class="bracket">
                <label for ="teamName">Team Name:</label>
                <input type="text" id="teamName11" class="bracketText" v-model="teamName" required />
            </div>

            <div class="bracket">
                <label for ="teamName">Team Name:</label>
                <input type="text" id="teamName12" class="bracketText" v-model="teamName" required />
            </div>

            <div class="bracket">
                <label for ="teamName">Team Name:</label>
                <input type="text" id="teamName13" class="bracketText" v-model="teamName" required />
            </div>

            <div class="bracket">
                <label for ="teamName">Team Name:</label>
                <input type="text" id="teamName14" class="bracketText" v-model="teamName" required />
            </div>

            <div class="bracket">
                <label for ="teamName">Team Name:</label>
                <input type="text" id="teamName15" class="bracketText" v-model="teamName" required />
            </div>

            <div class="bracket">
                <label for ="teamName">Team Name:</label>
                <input type="text" id="teamName16" class="bracketText" v-model="teamName" required />
            </div>

        </div>
        <div v-if="numTeams > 4">
            <div class="roundTitle">Round</div>

            <div class="bracket">
                <label for ="teamName">Team Name:</label>
                <input type="text" id="teamName1" class="bracketText" v-model="teamName" required />
            </div>

            <div class="bracket">
                <label for ="teamName">Team Name:</label>
                <input type="text" id="teamName2" class="bracketText" v-model="teamName" required />
            </div>

            <div class="bracket">
                <label for ="teamName">Team Name:</label>
                <input type="text" id="teamName3" class="bracketText" v-model="teamName" required />
            </div>

            <div class="bracket">
                <label for ="teamName">Team Name:</label>
                <input type="text" id="teamName4" class="bracketText" v-model="teamName" required />
            </div>

            <div class="bracket">
                <label for ="teamName">Team Name:</label>
                <input type="text" id="teamName5" class="bracketText" v-model="teamName" required />
            </div>

            <div class="bracket">
                <label for ="teamName">Team Name:</label>
                <input type="text" id="teamName6" class="bracketText" v-model="teamName" required />
            </div>

            <div class="bracket">
                <label for ="teamName">Team Name:</label>
                <input type="text" id="teamName7" class="bracketText" v-model="teamName" required />
            </div>

            <div class="bracket">
                <label for ="teamName">Team Name:</label>
                <input type="text" id="teamName8" class="bracketText" v-model="teamName" required />
            </div>

        </div>
        <div v-if="numTeams > 2">
            <div class="roundTitle">Round</div>

            <div class="bracket">
                <label for ="teamName">Team Name:</label>
                <input type="text" id="teamName1" class="bracketText" v-model="teamName" required />
            </div>

            <div class="bracket">
                <label for ="teamName">Team Name:</label>
                <input type="text" id="teamName2" class="bracketText" v-model="teamName" required />
            </div>

            <div class="match">
                <div class="bracket">
                    <label for ="teamName">Team Name:</label>
                    <input type="text" id="teamName3" class="bracketText" v-model="teamName" required />
                </div>

                <div class="bracket">
                    <label for ="teamName">Team Name:</label>
                    <input type="text" id="teamName4" class="bracketText" v-model="teamName" required />
                </div>
            </div>
        </div>
        <div v-if="numTeams >= 2">
            <div class="roundTitle">Round</div>

            <div class="match">
                <div class="bracket">
                    <label for ="teamName">Team Name:</label>
                    <input type="text" id="teamName" class="bracketText" v-model="teamName" required />
                </div>

                <div class="bracket">
                    <label for ="teamName">Team Name:</label>
                    <input type="text" id="teamName" class="bracketText" v-model="teamName" required />
                </div> 
            </div>
            

        </div>
        <div v-if="numTeams >= 2">
            <div class="roundTitle">WINNER</div>

                <div class="bracket" id="winner">
                        <label for ="teamName" class="bracketLabel">Team Name:</label>
                        <input type="text" id="teamName" class="bracketText" v-model="teamName" required />
                </div>

        </div>
    </div>    
</template>

<script>

export default {
    data() {
        return {
            numTeams: 2,
        }
    }
}

</script>

<style>
.roundTitleBar {
    display: flex;
    justify-content: space-between;
    padding: 0 50px;
}
#numTeams {
    display: flex;
    justify-content: center;
    align-items: center;
    text-align: center;
    color: purple;
    background-color: rgb(246, 240, 246);
    border-radius: 8px;
    width: 50px;
    height: 5px;
    margin: 10px auto;
    padding: 20px;
    box-shadow: 2px 2px 5px darkblue;
}
.numTeamsTitle {
    display: flex;
    justify-content: center;
    align-items: center;
    text-align: center;
    color: cyan;
    background-color: darkmagenta;
    border-radius: 8px;
    width: 150px;
    height: 20px;
    margin: 10px auto;
    padding: 20px;
    box-shadow: 2px 2px 5px darkblue;
}
.roundTitle {
    display: flex;
    justify-content: center;
    align-items: center;
    text-align: center;
    color: cyan;
    background-color: darkmagenta;
    border-radius: 8px;
    width: 150px;
    height: 20px;
    margin: 10px auto;
    padding: 20px;
    box-shadow: 2px 2px 5px darkblue;
}
.bracket {
    display: flex;
    flex-direction: row;
    justify-content: center;
    align-items: center;
    text-align: center;
    color: cyan;
    background-color: darkmagenta;
    border-radius: 8px;
    width: 150px;
    height: 20px;
    margin: 30px auto;
    padding: 20px;
    box-shadow: 2px 2px 5px darkblue;
}
.match {
    background-color: lightpink;
    border-radius: 8px;
    padding: 1px 20px;
}

</style>