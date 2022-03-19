import AddPerson from './components/AddPerson.vue'
import Home from './components/Home.vue'

export default[{
    path:'/',
    name: 'Home',
    component: Home
},
{
    path:'/add-person',
    name: 'AddPerson',
    component: AddPerson
}]