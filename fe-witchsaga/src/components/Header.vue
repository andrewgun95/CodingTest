<template>
    <div>
        <b-row>
            <b-col>
                <b-nav class="nav justify-content-sm-center">
                    <b-nav-item>
                        <router-link to="/" exact>VILLAGES</router-link>
                    </b-nav-item>
                    <b-nav-item>
                        <router-link to="/add-person" exact>ADD PERSON</router-link>
                    </b-nav-item>
                </b-nav>
            </b-col>
        </b-row>
    </div>
</template>

<script>
    export default {

    }
</script>

<style scoped>
    ul {
        list-style-type: none;
        text-align: right;
        margin: 0;
    }

    li {
        display: absolute;
        margin: 0 10px;
    }

    li a {
        text-decoration: none;
    }

    li a:hover {
        color: #444;
    }

    a {
        color: #fff;
        text-decoration: none;
        padding: 6px 8px;
        border-radius: 10px;
    }

    .nav {
        background: #ffdfba;
        padding: 14px 0;
        margin-bottom: 40px;
        box-shadow: 0 7px 7px -7px rgba(0, 0, 0, .5);
    }

    .router-link-active {
        background: #eee;
        color: #444;
    }
</style>