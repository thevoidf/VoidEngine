#version 130

in vec3 out_color;

out vec4 color;

void main()
{
    color = vec4(out_color, 1);
}