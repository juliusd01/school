import numpy as np
import plotly.graph_objects as go

# IS-LM model parameters
C = 100   # Consumption function parameters
I = 50    # Investment function parameters
G = 80    # Government spending
T = 30    # Taxes
M = 120   # Money supply
Lr = 0.2  # Liquidity preference parameters
Y_bar = 200  # Potential output

# Create a range of interest rates
r_range = np.linspace(0, 0.5, 100)

# IS curve: Y = C + I(r) + G
IS_curve = C + I - r_range

# LM curve: M = L(r, Y)
LM_curve = M / (Lr * Y_bar) - r_range

# Equilibrium point: Intersection of IS and LM curves
equilibrium_r = (C + I - G - T) / (Lr * Y_bar)
equilibrium_Y = C + I + G + equilibrium_r * Y_bar

# Create the plot
fig = go.Figure()

# Add IS curve to the plot
fig.add_trace(go.Scatter(x=r_range, y=IS_curve, mode='lines', name='IS Curve'))

# Add LM curve to the plot
fig.add_trace(go.Scatter(x=r_range, y=LM_curve, mode='lines', name='LM Curve'))

# Add equilibrium point to the plot
fig.add_trace(go.Scatter(x=[equilibrium_r], y=[equilibrium_Y], mode='markers', name='Equilibrium'))

# Set layout
fig.update_layout(
    title="IS-LM Model Visualization",
    xaxis_title="Interest Rate (r)",
    yaxis_title="Output (Y)"
)

# Save the plot
fig.write_html("IS-LM_Model/graphics.html")