import React, { Component } from 'react';

class Liste_ami extends Component{
	constructor(props){
		super(props);
		//this.state={liste:[{ami:'Remi Susceptible'},{ami:'Joris LECON'}]};
	}
	GoProfil(ami){
		this.props.profil(ami);
	}
/*
	render() {
		var temp;
		//alert(this.props.liste_ami === 'empty');
		if(this.props.liste_ami === 'empty' || this.props.liste_ami === undefined ){
			temp=<label>Pas d'amis</label>;
		}
		else { 
			temp=this.props.liste_ami.map((amis) => {
					return <p> {amis}</p>;
			});
		}
			console.log(this.props.liste_ami);

		return (
				<div className="col-md-3">
                    	<div className="card">
                        	<div className="card-body">
                            	<div className="h2">Liste Amis
                            	<br></br>
                            	{temp}

                           		 </div>
                            	
                            	
                        	</div>
                    	</div>
                	</div>

		
		);

	}
*/
	render() {
		var temp;
		//alert(this.props.liste_ami === 'empty');
		if(this.props.liste_ami === 'empty' || this.props.liste_ami === undefined ){
			temp=<label>Pas d'amis</label>;
		}
		else { 
			temp=this.props.liste_ami.map(amis => {
					return <li><input type="button" className="btn line-h" onClick={() => this.GoProfil(amis.friend)} value={amis.friend}/></li>;
			});
		}

		return (
			<div>
				<h5>Liste des amis :</h5>
				<ul>
				{temp}
				</ul>
			</div>
		);
	}


}


export default Liste_ami;