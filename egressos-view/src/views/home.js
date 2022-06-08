import { TabMenu } from 'primereact/tabmenu'
import React from 'react'

class Home extends React.Component {
    render() {
        return (
            <div>
                <TabMenu/>
                    
                <h1>Home</h1>
                <p>
                    This is the home page.
                </p>
            </div>
        )
    }
}

export default Home