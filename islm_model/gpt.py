import dash
from dash import dcc
from dash import html
from dash.dependencies import Input, Output
import numpy as np
import plotly.graph_objects as go

def get_Y(i, C0=600, c1=0.1, T=50, b0=50, b1=0.5, b2=0.8, G=100):
    Y = (1 / (1 - c1 - b1)) * (C0 - c1 * T + b0 + G) - (b2 / (1 - c1 - b1)) * i
    return Y

app = dash.Dash(__name__)

app.layout = html.Div([
    dcc.Input(id='i-low', type='number', value=0, placeholder='i_low'),
    dcc.Input(id='i-high', type='number', value=1000, placeholder='i_high'),
    dcc.Graph(id='money-market-plot')
])

@app.callback(
    Output('money-market-plot', 'figure'),
    [Input('i-low', 'value'),
     Input('i-high', 'value')]
)
def update_plot(i_low, i_high):
    money_market_dict = {}
    for i in range(i_low, i_high + 1, 25):
        money_market_dict[i / 100] = get_Y(i / 100)
    
    interest_rates = list(money_market_dict.keys())
    output_Y = list(money_market_dict.values())
    
    fig = go.Figure()
    fig.add_trace(go.Scatter(x=output_Y, y=interest_rates, mode='lines', name='Money Market'))
    fig.update_layout(title='Money Market', xaxis_title='Output (Y)', yaxis_title='Interest Rate')
    
    return fig

if __name__ == '__main__':
    app.run(debug=True)