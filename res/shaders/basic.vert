#version 130

in vec3 in_position;
in vec2 in_uvs;
in vec3 in_color;

out vec3 out_color;
out vec2 out_uvs;

uniform mat4 pr;
uniform mat4 model;

void main()
{
    gl_Position = pr * model * vec4(in_position, 1.0);

    out_color = in_color;
    out_uvs = in_uvs;
}