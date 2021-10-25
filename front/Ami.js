import React, { Component } from 'react';

class Ami extends Component{
	constructor(props){
		super(props);
		this.state={ami:this.props.ami};
	}

	render() {
		return (
			<div className="Ami"> {this.state.ami} </div>
		);
	}
}
export default Ami;