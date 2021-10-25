import React from "react"
import './css/bootstrap.min.css';
import './css/Principale.css';
import axios from "axios";
import logo from './html-css/images/Logo_White.png';
class NavBar extends React.Component{

  search(requete){
    alert("a faire");
  }
  profil(){
    this.props.profil("me");
  }
  deconnexion(){
    this.props.logout();
    const url= new URLSearchParams();
    url.append("key",this.props.Ukey);
    axios.get("http://localhost:8080/Twister/auth/logout?"+url).then(res=> this.resplogin(res));

}
  resplogin(obj){
    

    const valeur = JSON.parse(JSON.stringify(obj));
      if(valeur.message === undefined){
        alert("Vous etes déconnecté");
      }else{
           this.props.logout();

      }  
    }
  

    principale(){
    this.props.principale();
  }
  
  render(){
    return(

 <div id="bprincipale">

      <div>
      <nav className="navbar navbar-expand-md navbar-light tete">
      <input type="image" width="50px" alt="Logo" src={logo} onClick={((event)=>this.principale())}/>

      <div className="collapse navbar-collapse" id="navbarSupportedContent">
        <form className="form-inline searchbar">
          <input className="form-control mr-sm-2" type="search" placeholder="@user" aria-label="Search"></input>
          <button className="btn btn-outline-light my-2 my-sm-0" type="submit" onClick={()=>this.search()}>Recherche</button>

        </form>
      </div>
      <div>
      <button type="submit" className="btn btn-outline-light my-2 my-sm-0" href="#" onClick={()=>this.deconnexion()}>Déconnexion</button>
      </div>
    </nav>
    </div>
    </div>


    );
  }
}

export default NavBar;

